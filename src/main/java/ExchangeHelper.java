import java.util.concurrent.Exchanger;

public class ExchangeHelper {

    Exchanger<JSONEntity> exchanger = new Exchanger<JSONEntity>();
    JSONEntity jsonStorage = new JSONEntity();
    JSONEntity jsonThread = new JSONEntity();
    private static volatile ExchangeHelper instance;

    public static ExchangeHelper getInstance() {
        ExchangeHelper localInstance = instance;
        if (localInstance == null) {
            synchronized (ExchangeHelper.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ExchangeHelper();
                }
            }
        }
        return localInstance;
    }

    public JSONEntity getJsonStorage() {
        return jsonStorage;
    }

    public JSONEntity getJsonThread() {
        return jsonThread;
    }

    public Exchanger<JSONEntity> getExchanger() {
        return exchanger;
    }

       public  void exchangeJson() {
           try {
               jsonStorage = exchanger.exchange(jsonStorage);
           } catch (InterruptedException e) {
               System.out.println(e);
           }
       }
}
