package io.flutter.flutter_android_mate_data;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterAndroidMateDataPlugin */
public class FlutterAndroidMateDataPlugin implements FlutterPlugin, MethodCallHandler {
   static final String PLUGIN_NAME = "flutter_android_mate_data";
   static Application application;

  //此处是新的插件加载注册方式
  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    MethodChannel  mMethodChannel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), PLUGIN_NAME);
    application = (Application) flutterPluginBinding.getApplicationContext();
    mMethodChannel.setMethodCallHandler(this);
  }

  //旧的注册插件方式
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), PLUGIN_NAME);
    channel.setMethodCallHandler(new FlutterAndroidMateDataPlugin());
    application = (Application) registrar.context().getApplicationContext();
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if(call.method.equals("getMateDataValue")){

      String metaDataValue = "";
      try{

      ApplicationInfo appInfo = application.getPackageManager().getApplicationInfo(application.getPackageName(),PackageManager.GET_META_DATA);
       metaDataValue = appInfo.metaData.getString(call.arguments().toString());

    } catch (PackageManager.NameNotFoundException e) {
        e.printStackTrace();
      }

      result.success(metaDataValue);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {

  }

}
