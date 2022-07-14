package com.hit.product.applications.constants;

public class UrlConstant {

  public static class Banner {
    private Banner() {
    }

    private static final String PRE_FIX = "/banners";
    public static final String DATA_BANNER = PRE_FIX;
    public static final String DATA_BANNER_ID = PRE_FIX + "/{id}";
    public static final String DATA_BANNER_CREATE = PRE_FIX + "/create";
  }

  public static class Bill {
    private Bill() {
    }

    private static final String PRE_FIX = "/bills";
    public static final String DATA_BILL = PRE_FIX;
    public static final String DATA_BILL_ID = PRE_FIX + "/{id}";
    public static final String DATA_BILL_CREATE = PRE_FIX + "/{idUser}/create";
  }

  public static class Category {
    private Category() {
    }

    private static final String PRE_FIX = "/categories";
    public static final String DATA_CATEGORY = PRE_FIX;
    public static final String DATA_CATEGORY_ID = PRE_FIX + "/{id}";
    public static final String DATA_CATEGORY_CREATE = PRE_FIX + "/create";
  }

  public static class Comment {
    private Comment() {
    }

    private static final String PRE_FIX = "/comments";
    public static final String DATA_COMMENT = PRE_FIX;
    public static final String DATA_COMMENT_ID = PRE_FIX + "/{id}";
    public static final String DATA_COMMENT_RATE_BY_USER = PRE_FIX + "/{idUser}/{idProductRate}";
    public static final String DATA_COMMENT_CHILD_RATE_BY_USER = PRE_FIX + "/{idUser}/{idProductRate}/{idCommentParent}";
  }

  public static class DetailBill {
    private DetailBill() {
    }

    private static final String PRE_FIX = "/detail-bills";
    public static final String DATA_DETAIL_BILL = PRE_FIX;
    public static final String DATA_DETAIL_BILL_ID = PRE_FIX + "/{id}";
    public static final String DATA_DETAIL_BILL_CREATE = PRE_FIX + "/{idProduct}/create";
  }

  public static class EmailNotification {
    private EmailNotification() {
    }

    private static final String PRE_FIX = "/email-notifications";
    public static final String DATA_EMAIL_NOTIFICATION = PRE_FIX;
    public static final String DATA_EMAIL_NOTIFICATION_SIGN_UP = PRE_FIX + "/signUp";
    public static final String DATA_EMAIL_NOTIFICATION_CANCEL = PRE_FIX + "/cancel";
  }

  public static class EmailNotificationToken {
    private EmailNotificationToken() {
    }

    private static final String PRE_FIX = "/email-notification-tokens";
    public static final String DATA_EMAIL_NOTIFICATION_TOKEN = PRE_FIX;
    public static final String DATA_EMAIL_NOTIFICATION_TOKEN_ID = PRE_FIX + "/{id}";
    public static final String DATA_EMAIL_NOTIFICATION_TOKEN_SEARCH = PRE_FIX + "/search";
    public static final String DATA_EMAIL_NOTIFICATION_TOKEN_STATUS = PRE_FIX + "/{id}/status";
    public static final String DATA_EMAIL_NOTIFICATION_TOKEN_AVATAR = PRE_FIX + "/{id}/avatar";
    public static final String DATA_EMAIL_NOTIFICATION_TOKEN_EXPORT = PRE_FIX + "/export";
    public static final String DATA_EMAIL_NOTIFICATION_TOKEN_IMPORT = PRE_FIX + "/import";
  }

  public static class Event {
    private Event() {
    }

    private static final String PRE_FIX = "/events";
    public static final String DATA_EVENT = PRE_FIX;
    public static final String DATA_EVENT_ID = PRE_FIX + "/{id}";
    public static final String DATA_EVENT_CREATE = PRE_FIX + "/create";
  }

  public static class Help {
    private Help() {
    }

    private static final String PRE_FIX = "/helps";
    public static final String DATA_HELP = PRE_FIX;
    public static final String DATA_HELP_ID = PRE_FIX + "/{id}";
    public static final String DATA_HELP_CREATE = PRE_FIX + "/{idUser}/create";
  }

  public static class Image {
    private Image() {
    }

    private static final String PRE_FIX = "/images";
    public static final String DATA_IMAGE = PRE_FIX;
    public static final String DATA_IMAGE_ID = PRE_FIX + "/{id}";
  }

  public static class News {
    private News() {
    }

    private static final String PRE_FIX = "/news";
    public static final String DATA_NEWS = PRE_FIX;
    public static final String DATA_NEWS_ID = PRE_FIX + "/{id}";
    public static final String DATA_NEWS_CREATE = PRE_FIX + "/create";
  }

  public static class Notification {
    private Notification() {
    }

    private static final String PRE_FIX = "/notifications";
    public static final String DATA_NOTIFICATION = PRE_FIX;
    public static final String DATA_NOTIFICATION_ID = PRE_FIX + "/{id}";
    public static final String DATA_NOTIFICATION_CREATE = PRE_FIX + "/create";
    public static final String DATA_NOTIFICATION_FOR_USER = PRE_FIX + "/to-user/{idUser}";
  }

  public static class PasswordResetToken {
    private PasswordResetToken() {
    }

    private static final String PRE_FIX = "/password-reset-tokens";
    public static final String DATA_RESET_PASSWORD = PRE_FIX + "/resetPassword";
    public static final String DATA_SAVE_PASSWORD = PRE_FIX + "/savePassword";
    public static final String DATA_CHANGE_PASSWORD = PRE_FIX + "/changePassword";
  }

  public static class Product {
    private Product() {
    }

    private static final String PRE_FIX = "/products";
    public static final String DATA_PRODUCT = PRE_FIX;
    public static final String DATA_PRODUCT_ID = PRE_FIX + "/{id}";
    public static final String DATA_PRODUCT_SEARCH = PRE_FIX + "/search";
    public static final String DATA_PRODUCT_NEWS = PRE_FIX + "/news";
    public static final String DATA_PRODUCT_SELL_BEST = PRE_FIX + "/sell-best";
    public static final String DATA_PRODUCT_FILTER = PRE_FIX + "/filters";
    public static final String DATA_PRODUCT_SORT = PRE_FIX + "/sort";
    public static final String DATA_PRODUCT_CREATE = PRE_FIX + "/create/{idCategory}";
  }

  public static class ProductColor {
    private ProductColor() {
    }

    private static final String PRE_FIX = "/product-colors";
    public static final String DATA_PRODUCT_COLOR = PRE_FIX;
    public static final String DATA_PRODUCT_COLOR_ID = PRE_FIX + "/{id}";
    public static final String DATA_PRODUCT_COLOR_CREATE = PRE_FIX + "/create";
  }

  public static class ProductSize {
    private ProductSize() {
    }

    private static final String PRE_FIX = "/product-sizes";
    public static final String DATA_PRODUCT_SIZE = PRE_FIX;
    public static final String DATA_PRODUCT_SIZE_ID = PRE_FIX + "/{id}";
    public static final String DATA_PRODUCT_SIZE_CREATE = PRE_FIX + "/create";
  }

  public static class ProductRate {
    private ProductRate() {
    }

    private static final String PRE_FIX = "/product-rates";
    public static final String DATA_PRODUCT_RATE = PRE_FIX;
    public static final String DATA_PRODUCT_RATE_ID = PRE_FIX + "/{id}";
    public static final String DATA_PRODUCT_RATE_CREATE = PRE_FIX + "/rate/{idProduct}/{idUser}";
  }

  public static class User {
    private User() {
    }

    private static final String PRE_FIX = "/users";
    public static final String DATA_USER = PRE_FIX;
    public static final String DATA_USER_ID = PRE_FIX + "/{id}";
    public static final String DATA_USER_VOUCHERS = PRE_FIX + "/{id}/vouchers";
    public static final String DATA_USER_USE_VOUCHER = PRE_FIX + "/{id}/vouchers/{idVoucher}";
    public static final String DATA_USER_AVATAR = PRE_FIX + "/{id}/avatar";
  }

  public static class Role {
    private Role() {
    }

    private static final String PRE_FIX = "/roles";
    public static final String DATA_ROLE = PRE_FIX;
    public static final String DATA_ROLE_ID = PRE_FIX + "/{id}";
    public static final String DATA_ROLE_CREATE = PRE_FIX + "/create";
  }

  public static class UserRole {
    private UserRole() {
    }

    private static final String PRE_FIX = "/user-role";
    public static final String DATA_USER_ROLE = PRE_FIX;
    public static final String DATA_USER_ROLE_ID = PRE_FIX + "/{userId}";
    public static final String DATA_USER_ROLE_ADD_ROLE = PRE_FIX + "/add/{userId}/{roleId}";
    public static final String DATA_USER_ROLE_REMOVE_ROLE = PRE_FIX + "/remove/{userId}/{roleId}";
  }


  public static class Auth {
    private Auth() {
    }

    private static final String PRE_FIX = "/auth";
    public static final String LOGIN = PRE_FIX + "/login";
    public static final String GOOGLE = PRE_FIX + "/login/google";
    public static final String FACEBOOK = PRE_FIX + "/login/facebook";
    public static final String SIGNUP = PRE_FIX + "/signup";
    public static final String VALIDATE = PRE_FIX + "/validate";
    public static final String LOGOUT = PRE_FIX + "/logout/{id}";
  }

  public static class VerificationToken {
    private VerificationToken() {
    }

    private static final String PRE_FIX = "/product-rates";
    public static final String DATA_VERIFICATION_TOKEN = PRE_FIX;
    public static final String DATA_VERIFICATION_TOKEN_ID = PRE_FIX + "/{id}";
    public static final String DATA_VERIFICATION_TOKEN_VERIFY_REGISTRATION = PRE_FIX + "/verifyRegistration";
    public static final String DATA_VERIFICATION_TOKEN_VERIFY_EMAIL_NOTIFICATION = PRE_FIX + "/verifyEmailNotification";
    public static final String DATA_VERIFICATION_TOKEN_RESEND_VERIFY_TOKEN = PRE_FIX + "/resendVerifyToken";
  }

  public static class Voucher {
    private Voucher() {
    }

    private static final String PRE_FIX = "/vouchers";
    public static final String DATA_VOUCHER = PRE_FIX;
    public static final String DATA_VOUCHER_ID = PRE_FIX + "/{id}";
    public static final String DATA_VOUCHER_CREATE = PRE_FIX + "/create";
    public static final String DATA_VOUCHER_GET_VOUCHER = PRE_FIX + "/{idUser}/{idVoucher}";
    public static final String DATA_VOUCHER_AVATAR = PRE_FIX + "/{id}/avatar";
    public static final String DATA_VOUCHER_EXPORT = PRE_FIX + "/export";
    public static final String DATA_VOUCHER_IMPORT = PRE_FIX + "/import";
  }

}