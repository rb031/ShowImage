package ir.reza.showimaes.Utils;

public class CheckResponse
{

    public static final int SUCCESS_LOGIN = 1;
    public static final int SUCCESS_REGISTER = 2;
    public static final int SERVER_ERROR = 3;
    public static final int JSON_EXCEPTION = 4;
    public static final int SEND_SMS_RETREY = 5;
    public static final int VERIFY_CODE_ERROR = 6;
    public static final int USER_N_AVAILABLE= 7;
    public static final int USER_AVAILABLE = 8;
    public static final int IMAGE_UPLOADED = 9;
    public static final int CALL_SUBMITE = 10;
    public static final int VERIFY_CALL_INFO = 11;
    public static final int UPDATE_CALL_INFO = 12;
    public static final int VERIFY_MOBILE_CALLINFO = 13;
    public static final int USER_FOLLOWED = 14;
    public static final int USER_UNFOLLOWED = 15;
    public static final int NO_CREATED_COMMENT = 16;
    public static final int CREATED_COMMENT = 17;
    public static final int CREATED_ADDRESS = 31;
    public static final int SUBMIT_PAY = 20;
    public static final int DELETE_POST = 25;
    public static final int ADD_PRODUCT_TO_BASKET = 30;
    public static final int SUBMIT_ORDER = 32;
    public static final int NO_CODE = 40;
    public static final int USED_CODE = 41;
    public static final int CODE_LIMITED = 42;
    public static final int SUBMITED_CODE = 43;
    public static final int TRUE_CODE = 44;
    public static final int FINAL_ORDER = 45;


    public static String getMessage(int state)
    {
        if (state == SUCCESS_LOGIN)
            return "کد تایید به شماره همراه وارد شده ارسال شد";
        else if (state == SUCCESS_REGISTER)
            return "کد تایید به شماره همراه وارد شده ارسال شد";
        else if (state == SERVER_ERROR)
            return "خطا در سرور،لطفا مجددا تلاش کنید";
        else if (state == JSON_EXCEPTION)
            return "خطا در پردازش داده ها،لطفا مجددا تلاش کنید";
        else if (state == SEND_SMS_RETREY)
            return "کد تایید مجددا به شماره همراه وارد شده ارسال شد";
        else if (state == VERIFY_CODE_ERROR)
            return "کد تایید وارد شده صحیح نیست";
        else if (state == USER_N_AVAILABLE)
            return "این نام کاربری توسط فرد دیگری ثبت شده است";
        else if (state == USER_AVAILABLE)
            return "این نام کاربری قابل استفاده است";
        else if (state == IMAGE_UPLOADED)
            return "عکس با موفقیت بارگذاری شد";
        else if (state == CALL_SUBMITE)
            return "تماس جدید با موفقیت ایجاد شد";
        else if (state == VERIFY_CALL_INFO)
            return "کد تایید به شماره همراه وارد شده ارسال شد";
        else if (state == UPDATE_CALL_INFO)
            return "اطلاعات تماس با موفقیت به روز شد";
        else if (state == VERIFY_MOBILE_CALLINFO)
            return "کد تایید به شماره همراه این اطلاعات تماس ارسال شد";
        else if (state == USER_FOLLOWED)
            return "این کاربر به لیست دنبال شوندگان شما اضافه شد";
        else if (state == USER_UNFOLLOWED)
            return "این کاربر از لیست دنبال شوندگان شما حذف شد";
        else if (state == DELETE_POST)
            return "آگهی با موفقیت حذف شد.";
        else if (state == ADD_PRODUCT_TO_BASKET)
            return "محصول به سبد خرید افزوده شد.";
        else
            return "خطا ناشناخته";
    }
}