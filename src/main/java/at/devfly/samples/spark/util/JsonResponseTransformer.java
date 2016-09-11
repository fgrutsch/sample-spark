package at.devfly.samples.spark.util;

import spark.ResponseTransformer;

public class JsonResponseTransformer implements ResponseTransformer {
    @Override
    public String render(Object o) {
        return JsonUtil.write(o);
    }
}
