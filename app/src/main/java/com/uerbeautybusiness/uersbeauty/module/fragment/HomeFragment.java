package com.uerbeautybusiness.uersbeauty.module.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.google.gson.JsonObject;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.uerbeautybusiness.uersbeauty.R;
import com.uerbeautybusiness.uersbeauty.base.BaseFragment;
import com.uerbeautybusiness.uersbeauty.dialogfragment.PictureDropFragment;
import com.uerbeautybusiness.uersbeauty.net.CommonObserver;
import com.uerbeautybusiness.uersbeauty.net.Transformer;
import com.uerbeautybusiness.uersbeauty.utils.LogUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;

import static android.app.Activity.RESULT_OK;

/**
 * @ClassName HomeFragment
 * @Description 首页
 * @Author gy
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.content)
    TextView mContent;  @BindView(R.id.iv_img)
    ImageView mIvImg;
    private File mCompressFile;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(View view) {
    }

    @Override
    public void initData() {
        sSharedApi.requestSportRecord("357")
                .compose(Transformer.switchSchedulers())
                .subscribe(new CommonObserver<JsonObject>(mStateLayout, mActivity) {
                    @Override
                    public void doOnNext(JsonObject s) {
                        mContent.setText(s.toString());
                    }
                });

    }

    @Override
    public void initListener() {
        mIvImg.setOnClickListener(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case CHOOSE_QUEST:
                // 图片选择结果回调
                // 例如 LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
            case CAMERA_QUEST:
                if (resultCode == RESULT_OK) {

                    // 图片选择结果回调
                    List<LocalMedia> cameraList = PictureSelector.obtainMultipleResult(data);
                    String compressPath = cameraList.get(0).getCompressPath();
                    LogUtils.e("tian","图片路径:"+compressPath);
                    RequestOptions options = new RequestOptions()
                            .centerCrop()
                            .error(R.mipmap.ic_launcher);
                    Glide.with(getActivity().getApplicationContext())
                            .asBitmap()
                            .load(compressPath)
                            .apply(options)
                            .into(new BitmapImageViewTarget(mIvImg) {
                                @Override
                                protected void setResource(Bitmap resource) {
                                    RoundedBitmapDrawable circularBitmapDrawable =
                                            RoundedBitmapDrawableFactory.create(getResources(), resource);
                                    circularBitmapDrawable.setCircular(true);
                                    mIvImg.setImageDrawable(circularBitmapDrawable);
                                }
                            });
                    if (!TextUtils.isEmpty(compressPath)) {
                        mCompressFile = new File(compressPath);
                    } else {
                    }

                }
                break;
        }
    }
    public final int CHOOSE_QUEST = 902;
    public final int CAMERA_QUEST = 903;
    private PictureDropFragment mPictureFragment;
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_img:
                if (mPictureFragment == null) {
                    mPictureFragment = new PictureDropFragment();
                }
                Bundle bundle = new Bundle();
                bundle.putInt("choose", CHOOSE_QUEST);
                bundle.putInt("camera", CAMERA_QUEST);
                bundle.putBoolean("hasVideo", false);
                bundle.putBoolean("drop", true);
                bundle.putInt("dropX", 1);
                bundle.putInt("dropY", 1);
                bundle.putInt("number", 1);
                mPictureFragment.setArguments(bundle);
                mPictureFragment.show(getFragmentManager(), null);
                break;
        }
    }
}
