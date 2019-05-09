    package action;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
 import java.io.OutputStream;
 import com.mongodb.MongoClient;
 import com.mongodb.client.FindIterable;
 import com.mongodb.client.MongoCollection;
 import com.mongodb.client.MongoCursor;
 import com.mongodb.client.MongoDatabase;
 import com.mongodb.client.model.Filters;
 import org.bson.Document;

 import java.util.ArrayList;
 import java.util.List;

import org.apache.commons.codec.binary.Base64;
 import org.bson.types.ObjectId;

public class picture {

    public static void main(String[] args) {

        String imgFile = "C:\\Users\\My\\Desktop\\7.jpg";//待处理的图片
        String imgbese=getImgStr(imgFile);
        System.out.println(imgbese.length());
        System.out.println(imgbese);
//        String imgFilePath = "C:\\Users\\My\\Desktop\\2.jpg";//新生成的图片
//       generateImage(imgbese,imgFilePath);


        // 连接到 mongodb 服务
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // 连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        System.out.println("Connect to database successfully");
        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        System.out.println("集合 test 选择成功");

        Document document = new Document("title", "MongoDB").append("description", "database").append("likes", imgbese);  //新建文档
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);  //添加文档（对应的BSON数据）
        System.out.println("文档插入成功");

    }
    /**
 27      * 将图片转换成Base64编码
 28      * @param imgFile 待处理图片
 29      * @return
 30      */
             public static String getImgStr(String imgFile){
                 //将图片文件转化为字节数组字符串，并对其进行Base64编码处理
                 InputStream in = null;
                 byte[] data = null;
                //读取图片字节数组
              try
               {
                   in = new FileInputStream(imgFile);
                   data = new byte[in.available()];
                   in.read(data);
                   in.close();
               }
               catch (IOException e)
               {
                   e.printStackTrace();
               }
              return new String(Base64.encodeBase64(data));
       }

         /**
 53      * 对字节数组字符串进行Base64解码并生成图片
 54      * @param imgStr 图片数据
 55      * @param imgFilePath 保存图片全路径地址
 56      * @return
 57      */
       public static boolean generateImage(String imgStr,String imgFilePath){
              //
             if (imgStr == null) //图像数据为空
                     return false;
             try
           {
               //Base64解码
               byte[] b = Base64.decodeBase64(imgStr);
                     for(int i=0;i<b.length;++i)
                        {
                           if(b[i]<0)
                                   {//调整异常数据
                                      b[i]+=256;
                                 }
                  }
                        //生成jpeg图片
                      OutputStream out = new FileOutputStream(imgFilePath);
                      out.write(b);
                      out.flush();
                      out.close();
                     return true;
                  }
             catch (Exception e)
                 {
                       return false;
                  }
            }
}
