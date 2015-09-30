package com.ekuater.httpfileloader.disc.impl.ext;

import com.ekuater.httpfileloader.disc.DiskCache;
import com.ekuater.httpfileloader.disc.naming.FileNameGenerator;
import com.ekuater.httpfileloader.utils.IoUtils;
import com.ekuater.httpfileloader.utils.L;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Disk cache based on "Least-Recently Used" principle. Adapter pattern, adapts
 * {@link com.ekuater.httpfileloader.disc.impl.ext.DiskLruCache DiskLruCache} to
 * {@link com.ekuater.httpfileloader.disc.DiskCache DiskCache}
 *
 * @see FileNameGenerator
 */
public class LruDiskCache implements DiskCache {

    /**
     * {@value
     */
    public static final int DEFAULT_BUFFER_SIZE = 32 * 1024; // 32 Kb

    private static final String ERROR_ARG_NULL = " argument must be not null";
    private static final String ERROR_ARG_NEGATIVE = " argument must be positive number";

    protected DiskLruCache cache;
    private File reserveCacheDir;

    protected final FileNameGenerator fileNameGenerator;

    protected int bufferSize = DEFAULT_BUFFER_SIZE;

    /**
     * @param cacheDir          Directory for file caching
     * @param fileNameGenerator {@linkplain com.ekuater.httpfileloader.disc.naming.FileNameGenerator
     *                          Name generator} for cached files. Generated names must match the regex
     *                          <strong>[a-z0-9_-]{1,64}</strong>
     * @param cacheMaxSize      Max cache size in bytes. <b>0</b> means cache size is unlimited.
     * @throws IOException if cache can't be initialized (e.g. "No space left on device")
     */
    public LruDiskCache(File cacheDir, FileNameGenerator fileNameGenerator, long cacheMaxSize) throws IOException {
        this(cacheDir, null, fileNameGenerator, cacheMaxSize, 0);
    }

    /**
     * @param cacheDir          Directory for file caching
     * @param reserveCacheDir   null-ok; Reserve directory for file caching. It's used when the primary directory isn't available.
     * @param fileNameGenerator {@linkplain com.ekuater.httpfileloader.disc.naming.FileNameGenerator
     *                          Name generator} for cached files. Generated names must match the regex
     *                          <strong>[a-z0-9_-]{1,64}</strong>
     * @param cacheMaxSize      Max cache size in bytes. <b>0</b> means cache size is unlimited.
     * @param cacheMaxFileCount Max file count in cache. <b>0</b> means file count is unlimited.
     * @throws IOException if cache can't be initialized (e.g. "No space left on device")
     */
    public LruDiskCache(File cacheDir, File reserveCacheDir, FileNameGenerator fileNameGenerator, long cacheMaxSize,
                        int cacheMaxFileCount) throws IOException {
        if (cacheDir == null) {
            throw new IllegalArgumentException("cacheDir" + ERROR_ARG_NULL);
        }
        if (cacheMaxSize < 0) {
            throw new IllegalArgumentException("cacheMaxSize" + ERROR_ARG_NEGATIVE);
        }
        if (cacheMaxFileCount < 0) {
            throw new IllegalArgumentException("cacheMaxFileCount" + ERROR_ARG_NEGATIVE);
        }
        if (fileNameGenerator == null) {
            throw new IllegalArgumentException("fileNameGenerator" + ERROR_ARG_NULL);
        }

        if (cacheMaxSize == 0) {
            cacheMaxSize = Long.MAX_VALUE;
        }
        if (cacheMaxFileCount == 0) {
            cacheMaxFileCount = Integer.MAX_VALUE;
        }

        this.reserveCacheDir = reserveCacheDir;
        this.fileNameGenerator = fileNameGenerator;
        initCache(cacheDir, reserveCacheDir, cacheMaxSize, cacheMaxFileCount);
    }

    private void initCache(File cacheDir, File reserveCacheDir, long cacheMaxSize, int cacheMaxFileCount)
            throws IOException {
        try {
            cache = DiskLruCache.open(cacheDir, 1, 1, cacheMaxSize, cacheMaxFileCount);
        } catch (IOException e) {
            L.e(e);
            if (reserveCacheDir != null) {
                initCache(reserveCacheDir, null, cacheMaxSize, cacheMaxFileCount);
            }
            if (cache == null) {
                throw e; //new RuntimeException("Can't initialize disk cache", e);
            }
        }
    }

    @Override
    public File getDirectory() {
        return cache.getDirectory();
    }

    @Override
    public File get(String fileUri) {
        DiskLruCache.Snapshot snapshot = null;
        try {
            snapshot = cache.get(getKey(fileUri));
            return snapshot == null ? null : snapshot.getFile(0);
        } catch (IOException e) {
            L.e(e);
            return null;
        } finally {
            if (snapshot != null) {
                snapshot.close();
            }
        }
    }

    @Override
    public boolean save(String fileUri, InputStream inputStream, IoUtils.CopyListener listener) throws IOException {
        DiskLruCache.Editor editor = cache.edit(getKey(fileUri));
        if (editor == null) {
            return false;
        }

        OutputStream os = new BufferedOutputStream(editor.newOutputStream(0), bufferSize);
        boolean copied = false;
        try {
            copied = IoUtils.copyStream(inputStream, os, listener, bufferSize);
        } finally {
            IoUtils.closeSilently(os);
            if (copied) {
                editor.commit();
            } else {
                editor.abort();
            }
        }
        return copied;
    }

    @Override
    public boolean remove(String fileUri) {
        try {
            return cache.remove(getKey(fileUri));
        } catch (IOException e) {
            L.e(e);
            return false;
        }
    }

    @Override
    public void close() {
        try {
            cache.close();
        } catch (IOException e) {
            L.e(e);
        }
        cache = null;
    }

    @Override
    public void clear() {
        try {
            cache.delete();
        } catch (IOException e) {
            L.e(e);
        }
        try {
            initCache(cache.getDirectory(), reserveCacheDir, cache.getMaxSize(), cache.getMaxFileCount());
        } catch (IOException e) {
            L.e(e);
        }
    }

    private String getKey(String imageUri) {
        return fileNameGenerator.generate(imageUri);
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
