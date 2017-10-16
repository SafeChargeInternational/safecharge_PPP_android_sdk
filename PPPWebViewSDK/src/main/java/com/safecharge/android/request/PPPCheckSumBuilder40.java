package com.safecharge.android.request;

import com.safecharge.android.request.models.PPItemDetails;
import com.safecharge.android.util.Constants;
import java.util.ArrayList;

/**
 * Default implementation of {@link ICheckSumBuilder} used to construct checksum for {@link PPRequest}
 * @author Bozhidar
 *
 */
public class PPPCheckSumBuilder40 implements ICheckSumBuilder {


    @Override
    public String buildCheckSum(PPRequest request, String secretKey) {


        if (secretKey == null) {
            throw new IllegalStateException("SecretKey can't be null !");
        }

        ArrayList<String> allValues = request.getValuesList();
        StringBuilder builder = new StringBuilder();
        builder.append(secretKey);
        for (String cvalue : allValues) {
            builder.append(cvalue);
        }


        return builder.toString();
    }
}