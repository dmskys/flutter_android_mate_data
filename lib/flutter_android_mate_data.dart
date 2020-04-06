import 'dart:async';

import 'package:flutter/services.dart';

class FlutterAndroidMateData {
  static const MethodChannel _channel =
      const MethodChannel('flutter_android_mate_data');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> getMateDataValue(String mateDataName) async {
    final String value =
        await _channel.invokeMethod('getMateDataValue', mateDataName);
    return value;
  }
}
