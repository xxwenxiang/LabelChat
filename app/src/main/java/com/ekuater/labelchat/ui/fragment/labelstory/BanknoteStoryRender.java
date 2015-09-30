package com.ekuater.labelchat.ui.fragment.labelstory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ekuater.labelchat.R;
import com.ekuater.labelchat.datastruct.LabelStory;
import com.ekuater.labelchat.delegate.AvatarManager;
import com.ekuater.labelchat.ui.UILauncher;
import com.ekuater.labelchat.ui.util.MiscUtils;
import com.ekuater.labelchat.ui.util.ShowToast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Leo on 2015/5/17.
 *
 * @author LinYong
 */
public class BanknoteStoryRender implements StoryContentRender, View.OnClickListener {

    private Context mContext;
    private TextView mDetailContent;
    private ImageView mDetailImage;

    private LabelStory mBoundStory;

    public BanknoteStoryRender(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.banknote_story_content, container, false);
        mDetailContent = (TextView) view.findViewById(R.id.banknote_content);
        mDetailImage = (ImageView) view.findViewById(R.id.banknote_image);
        mDetailImage.setOnClickListener(this);
        mDetailImage.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                MenuInflater inflater = new MenuInflater(mContext);
                inflater.inflate(R.menu.save_picture_menu, menu);
                menu.findItem(R.id.save).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        saveStoryImage();
                        return true;
                    }
                });
            }
        });
        return view;
    }

    @Override
    public void bindContentData(LabelStory story) {
        mBoundStory = story;
        bindStory();
    }

    @Override
    public void onDestroyView() {
    }

    @Override
    public void onDestroy() {
    }

    private void bindStory() {
        String content = mBoundStory.getContent();
        if (TextUtils.isEmpty(content)) {
            mDetailContent.setVisibility(View.GONE);
        } else {
            mDetailContent.setVisibility(View.VISIBLE);
            mDetailContent.setText(content);
        }
        loadContentImage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banknote_image:
                if (mBoundStory != null && mBoundStory.getImages() != null
                        && mBoundStory.getImages().length > 0) {
                    UILauncher.launchLabelStoryShowPhotoUI(mContext,
                            mBoundStory.getImages()[0]);
                }
                break;
            default:
                break;
        }
    }

    private void saveStoryImage() {
        Drawable drawable = mDetailImage.getDrawable();
        if (drawable != null && drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            MediaStore.Images.Media.insertImage(mContext.getContentResolver(),
                    bitmap, null, null);
            saveImage(bitmap);
            ShowToast.makeText(mContext, R.drawable.emoji_smile,
                    mContext.getResources().getString(R.string.saved)).show();
        }
    }

    private void saveImage(Bitmap bitmap) {
        File saveFilePath = new File(Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM);
        if (!saveFilePath.exists()) {
            try {
                if (saveFilePath.createNewFile()) {
                    FileOutputStream fos = new FileOutputStream(saveFilePath);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.flush();
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadContentImage() {
        String[] images = mBoundStory.getImages();
        if (images != null && images.length > 0) {
            String image = images[0];
            mDetailImage.setVisibility(View.VISIBLE);
            MiscUtils.showLabelStoryImage(AvatarManager.getInstance(mContext),
                    image, mDetailImage, R.drawable.pic_loading);
        } else {
            mDetailImage.setVisibility(View.GONE);
        }
    }
}
