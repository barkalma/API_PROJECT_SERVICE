package com.config;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Application {
  private static final String BUNDLE_NAME = "com.config.dao"; //$NON-NLS-1$

  private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

  private Application() {}

  public static String getString(String key) {
    try {
      return RESOURCE_BUNDLE.getString(key);
    } catch (MissingResourceException e) {
      return '!' + key + '!';
    }
  }
}
