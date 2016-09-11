package at.devfly.samples.util;

import spark.ResponseTransformer;

public class JsonResponseTransformer implements ResponseTransformer {
    @Override
    public String render(Object o) {
        return JsonUtil.write(o);
    }
}
