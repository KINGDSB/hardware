package com.honeylovely.center.common.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProConfiguration {

  private static final Logger logger = LoggerFactory.getLogger(ProConfiguration.class);

  private static String nginxPath;
  private static String ckImageRedisPath;
  private static String ftpUpload;
  private static String tomcatPath;
  private static String marketingRecommendUrl;
  private static String marketingPromotionUrl;

  public static String getMarketingRecommendUrl() {
    return marketingRecommendUrl;
  }

  public static Logger getLogger() {
    return logger;
  }

  public static String getNginxPath() {
    return nginxPath;
  }
  

  public static String getCkImageRedisPath() {
    return ckImageRedisPath;
  }

  public static String getFtpUpload() {
    return ftpUpload;
  }

  public static String getTomcatPath() {
    return tomcatPath;
  }

  public static String getMarketingPromotionUrl() {
    return marketingPromotionUrl;
  }

  static {
    final Properties properties = new Properties();
    try {
      properties.load(Thread.currentThread().getContextClassLoader()
          .getResourceAsStream("application.properties"));
      nginxPath = properties.getProperty("nginx.path");
      ckImageRedisPath = properties.getProperty("ck.imageRedisPath");
      ftpUpload = properties.getProperty("file.ftp.upload");
      tomcatPath = properties.getProperty("file.tomcatPath");
      marketingRecommendUrl = properties.getProperty("marketing.recommend.url");
      marketingPromotionUrl = properties.getProperty("marketing.promotion.url");
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
  }
}
