package at.devfly.samples.spark.settings;

public class SparkSettings {

    private Integer port;
    private Integer maxThreads;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(Integer maxThreads) {
        this.maxThreads = maxThreads;
    }

    @Override
    public String toString() {
        return "SparkSettings{" +
                "port=" + port +
                ", maxThreads=" + maxThreads +
                '}';
    }

}
