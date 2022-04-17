package io.kimmking.dtx01.datasource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DatasourceHolder {

    private static final ThreadLocal<String> datasourceLocal = new ThreadLocal<>();

    public static void setRead(){
        datasourceLocal.set("read");
        log.info("切换到读库");
    }
    public static void setWrite(){
        datasourceLocal.set("write");
        log.info("切换到写库");
    }
    public static String get(){
        return datasourceLocal.get();
    }
    public static void clear(){
        datasourceLocal.remove();
    }
}
