package hr.config.multitenancy;

public class TenantContextHolder {

    private static ThreadLocal<TenantEnum> threadLocal = new ThreadLocal<>();

    public static void setTenantContext(TenantEnum tenantEnum){
        threadLocal.set(tenantEnum);

    }

    public static void clearTenantContext(){
        threadLocal.remove();
    }

    public static TenantEnum getTenantContext(){
        return threadLocal.get();
    }



}
