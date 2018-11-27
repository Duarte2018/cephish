import java.util.UUID;

//falta aqui muita coisa a fazer
public class ObjectStorageDevice {
    String name;
    ClusterMap clusterMap;
    String status;
    boolean upToDate;
    int osdID;
    UUID fsid;
    String fileSystem;


    public ObjectStorageDevice(String name, String filesystem, int osdID){
        this.osdID = osdID;
        this.name = name;
        this.clusterMap = null;
        this.status = "UP";
        this.upToDate = false;
        this.fsid = UUID.randomUUID();
        this.fileSystem = filesystem;
    }
}
