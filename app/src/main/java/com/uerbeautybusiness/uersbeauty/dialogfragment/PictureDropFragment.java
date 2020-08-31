package com.uerbeautybusiness.uersbeauty.dialogfragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uerbeautybusiness.uersbeauty.R;
import com.uerbeautybusiness.uersbeauty.application.MyApplication;
import com.uerbeautybusiness.uersbeauty.base.BaseDialogFragment;
import com.uerbeautybusiness.uersbeauty.dialog.DialogHelper;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


/**
 * Description:选择图片fragment界面
 * Author:gy
 * Email:447508959@qq.com
 * Date:${DATE}.
 */

public class PictureDropFragment extends BaseDialogFragment {

    @BindView(R.id.avatar_tv_photo)
    TextView mAvatarTvPhoto;
    @BindView(R.id.avatar_tv_camera)
    TextView mAvatarTvCamera;
    @BindView(R.id.avatar_tv_cancel)
    TextView mAvatarTvCancel;
    @BindView(R.id.avatar_tv_video)
    TextView mAvatarTvVideo;
    @BindView(R.id.avatar_view)
    View mAvatarView;
    private static final String TAG = "PictureFragment";
    private RxPermissions mRxPermission;
    private Activity mActivity;

    private static boolean isFirstPic;
    private static boolean isFirstCamera;
    private Context mContext;
    private int mCamera;
    private int mChoose;
    private int mMaxNumber;
    private boolean mIsDrop;
    private int mDropX = 4;
    private int mDropY = 3;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
        mContext = context;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initViews() {
        super.initViews();
        //初始化rxpermissions
        mRxPermission = new RxPermissions(mActivity);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mCamera = bundle.getInt("camera");
            mChoose = bundle.getInt("choose");
            mIsDrop = bundle.getBoolean("drop");
            if (mIsDrop) {
                mDropX = bundle.getInt("dropX");
                mDropY = bundle.getInt("dropY");
            }
            mMaxNumber = bundle.getInt("number");
            boolean hasVideo = bundle.getBoolean("hasVideo", true);
            if (!hasVideo) {
                mAvatarTvVideo.setVisibility(View.GONE);
                mAvatarView.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 点击事件.
     */
    @OnClick({R.id.avatar_tv_photo, R.id.avatar_tv_camera, R.id.avatar_tv_cancel, R.id.avatar_tv_video})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.avatar_tv_photo://相册
                mRxPermission
                        .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(@NonNull Permission permission) throws Exception {
                                if (permission.granted) {
                                    // 用户已经同意该权限
                                    initPhotoAlbum();
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                                } else {
                                    // 用户拒绝了该权限，并且选中『不再询问』
                                    if (isFirstPic) {
                                        showSettingDialog();
                                    }
                                    isFirstPic = true;
                                }
                            }
                        });
                dismiss();

                break;
            case R.id.avatar_tv_camera://相机
                mRxPermission
                        .requestEach(Manifest.permission.CAMERA)
                        .subscribe(permission -> {
                            if (permission.granted) {
                                // 用户已经同意该权限
                                initCamera();
                            } else if (permission.shouldShowRequestPermissionRationale) {
                                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                            } else {
                                // 用户拒绝了该权限，并且选中『不再询问』
                                if (isFirstCamera) {
                                    showSettingDialog();
                                }
                                isFirstCamera = true;
                            }
                        });
                dismiss();
                break;
            case R.id.avatar_tv_video://选择视频
                mRxPermission
                        .requestEach(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .subscribe(new Consumer<Permission>() {
                            @Override
                            public void accept(@NonNull Permission permission) throws Exception {
                                if (permission.granted) {
                                    // 用户已经同意该权限
                                    initVideo();
                                } else if (permission.shouldShowRequestPermissionRationale) {
                                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

                                } else {
                                    // 用户拒绝了该权限，并且选中『不再询问』
                                    if (isFirstPic) {
                                        showSettingDialog();
                                    }
                                    isFirstPic = true;
                                }
                            }
                        });
                dismiss();
                break;
            case R.id.avatar_tv_cancel://取消
                dismiss();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化相册.(视频选择)
     */
    private void initVideo() {
        PictureSelector.create(mActivity)
                .openGallery(PictureMimeType.ofVideo())
                .maxSelectNum(1)
                .enableCrop(true)
                .compress(true)
                .videoMaxSecond(15)
                .recordVideoSecond(15)
                .forResult(mChoose);
    }

    /**
     * 初始化相机.
     */
    private void initCamera() {
        if (mIsDrop) {
            PictureSelector.create(mActivity)
                    .openCamera(PictureMimeType.ofAll())
                    .maxSelectNum(mMaxNumber)
                    .enableCrop(true)
                    .withAspectRatio(mDropX, mDropY)
                    .compress(true)
                    .videoMaxSecond(15)
                    .recordVideoSecond(15)
                    .forResult(mCamera);
        } else {
            PictureSelector.create(mActivity)
                    .openCamera(PictureMimeType.ofAll())
                    .maxSelectNum(mMaxNumber)
                    .enableCrop(false)
                    .compress(true)
                    .videoMaxSecond(15)
                    .recordVideoSecond(15)
                    .forResult(mCamera);
        }


    }

    /**
     * 初始化相册.(图片选择)
     */
    private void initPhotoAlbum() {
        if (mIsDrop) {
            if (mMaxNumber == 1) {
                PictureSelector.create(mActivity)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .enableCrop(true)
                        .withAspectRatio(mDropX, mDropY)
                        .compress(true)
                        .forResult(mChoose);
            } else {
                PictureSelector.create(mActivity)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(mMaxNumber)
                        .enableCrop(true)
                        .withAspectRatio(mDropX, mDropY)
                        .compress(true)
                        .forResult(mChoose);
            }
        } else {
            if (mMaxNumber == 1) {
                PictureSelector.create(mActivity)
                        .openGallery(PictureMimeType.ofImage())
                        .selectionMode(PictureConfig.SINGLE)
                        .enableCrop(false)
                        .compress(true)
                        .forResult(mChoose);
            } else {
                PictureSelector.create(mActivity)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(mMaxNumber)
                        .enableCrop(false)
                        .compress(true)
                        .forResult(mChoose);
            }
        }

    }

    /**
     * 弹出是否进设置界面对话框.
     */
    public void showSettingDialog() {
        AlertDialog.Builder confirmDialog = DialogHelper.getConfirmDialog(
                MyApplication.getInstance(), "是否需要进入设置界面开启权限？",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //跳转设置界面打开权限
                        Intent localIntent = new Intent();
                        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        if (Build.VERSION.SDK_INT >= 15) {
                            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                            localIntent.setData(
                                    Uri.fromParts("package", MyApplication.getInstance().getPackageName(), null));
                        }
                        MyApplication.getInstance().startActivity(localIntent);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        confirmDialog.show();
    }
}
