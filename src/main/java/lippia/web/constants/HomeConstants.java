package lippia.web.constants;

public class HomeConstants {

    public static final String LOGIN_OPTION = "xpath://button[@class='LoginButton-button']";
    public static final String EMAIL_OPTION = "id:EmailLoginButton";
    public static final String EMAIL_INPUT = "name:email";
    public static final String PASSWORD_INPUT = "name:password";
    public static final String LOGIN_BUTTON = "xpath://button[@class='LoginForm-submit']";
    public static final String LOGOUT_BUTTON = "xpath://li[@class='LoginButton-logout']";
    public static final String FAILED_LOGIN_MESSAGE = "xpath://span[contains(text(),'El usuario o la contraseña son incorrectos')]";






    public static final String HOME_BUTTON_LOCATOR = "xpath://*[@id='content']/nav/a";
    public static final String HOME_SLIDER_LOCATOR = "xpath://button[@class='LoginButton-button']";
    public static final String ADD_BASKET_BUTTON_LOCATOR = "xpath://button[@class='single_add_to_cart_button button alt']";
    public static final String PRICE_LABEL_LOCATOR = "xpath://div[@itemprop='offers']//p[@class='price']/descendant::span[@class='woocommerce-Price-amount amount']";
    public static final String AMOUNT_LABEL_LOCATOR = "xpath://span[@class='amount']";
    public static final String SUBTOTAL_LABEL_LOCATOR = "xpath://tr[@class='cart-subtotal']";
    public static final String TOTAL_LABEL_LOCATOR = "xpath://tr[@class='order-total']";
    public static final String CHECKOUT_BUTTON_LOCATOR = "//a[@class='checkout-button button alt wc-forward']";
    public static final String BILLING_DETAIL_LABEL_LOCATOR = "xpath://h3[contains(text(),'Billing Details')]";
    public static final String ADITIONAL_INFORMATION_LABEL_LOCATOR = "xpath://h3[contains(text(),'Additional Information')]";
    public static final String FIRST_NAME_INPUT_LOCATOR = "id:billing_first_name";
    public static final String LAST_NAME_INPUT_LOCATOR = "id:billing_last_name";
    public static final String COMPANY_INPUT_LOCATOR = "id:billing_company";
    public static final String EMAIL_INPUT_LOCATOR = "id:billing_email";
    public static final String PHONE_INPUT_LOCATOR = "id:billing_phone";
    public static final String COUNTRY_SPAN_LOCATOR = "xpath://a[@class='select2-choice']";
    public static final String COUNTRY_SEARCH_INPUT_LOCATOR = "id:s2id_autogen1_search";
    public static final String ADDRESS_INPUT_LOCATOR = "id:billing_address_1";
    public static final String CITY_INPUT_LOCATOR = "id:billing_city";
    public static final String STATE_INPUT_LOCATOR = "xpath://a[@class='select2-choice select2-default']";
    public static final String STATE_SEARCH_INPUT_LOCATOR = "id:s2id_autogen2_search";
    public static final String ZIPCODE_INPUT_LOCATOR = "id:billing_postcode";
    public static final String PLACE_ORDER_BUTTON_LOCATOR = "id:place_order";
    public static final String TITLE_ORDER_DETAILS_LOCATOR = "xpath://h2[contains(text(),'Order Details')]";
    public static final String PRICE_ORDER_DETAILS_LOCATOR = "xpath://span[@class='woocommerce-Price-amount amount']//ancestor::td[@class='product-total']";
    public static final String PAYMENT_ORDER_DETAILS_LOCATOR = "xpath://th[contains(text(),'Payment Method:')]//following-sibling::td";
    public static final String TITLE_YOUR_ORDER_LOCATOR = "id:order_review_heading";
    public static final String PRODUCT_PRICE_LOCATOR = "xpath://td[@class='product-total']//span[@class='woocommerce-Price-amount amount']";
    public static final String IMAGE_ARRIVAL= "//img//following-sibling::h3[contains(text(),'%s')]" ;
    public static final String NEW_ARRIVAL_TITLE_LOCATOR = "xpath://h2[contains(text(),'new arrivals')]";
    public static final String PAYMENT_METHOD_XPATH = "//label[contains(text(),'%s')]//preceding-sibling::input";
    public static final String YOUR_ORDER_TABLE_LOCATOR = "xpath://*[@id='order_review']/table";
    public static final String ADD_COUPON_LOCATOR = "name:apply_coupon";
}
