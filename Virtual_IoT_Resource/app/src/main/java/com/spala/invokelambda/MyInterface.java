package com.spala.invokelambda;

import com.amazonaws.mobileconnectors.lambdainvoker.LambdaFunction;

/**
 * Created by Arif on 6/24/2017.
 */

public interface MyInterface {

    /**
     * Invoke lambda function "echo". The function name is the method name
     */
    @LambdaFunction
    String FirebaseServer(Info info);


}