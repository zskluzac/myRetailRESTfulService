package com.myretail.productsapi.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Constants {
    public static final String MY_RETAIL_URI_PREFIX = "https://redsky.target.com/v2/pdp/tcin/";
    public static final String MY_RETAIL_URI_SUFFIX = "?excludes=taxonomy,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics,deep_red_labels,available_to_promise_network,circle_offers";
    public static final String CURRENCY_CODE = "USD";
    public static final List<Integer> PRODUCT_ID_LIST = Collections.unmodifiableList(
            new ArrayList<Integer>() {{
                add(13860428);
                add(13860427);
                add(13860425);
            }});
}
