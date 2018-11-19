
//falta aqui muita coisa a fazer
public class ObjectStorageDevice {
    String name;
    ClusterMap clusterMap;
    String status;
    boolean upToDate;

    public ObjectStorageDevice(String name){
        this.name = name;
        this.clusterMap = null;
        this.status = "UP";
        this.upToDate = false;
    }
}
