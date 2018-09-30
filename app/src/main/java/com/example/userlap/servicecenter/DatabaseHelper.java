package com.example.userlap.servicecenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Student.db";
    public static final String Table_NAME_SERVICE="Service";
    public static final String COL0_Service="SeviceID";
    public static final String COL1_Service="service_name";
    public static final String COL2_Service="contact_no";
    public static final String COL3_Service="service_location";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+Table_NAME_SERVICE +" (SeviceID INTEGER PRIMARY KEY AUTOINCREMENT,service_name TEXT,contact_no TEXT,service_location TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Table_NAME_SERVICE);
        onCreate(db);
    }

    public boolean insert_service(String service_name,String contact_no,String service_location ){

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cvService=new ContentValues();
        cvService.put(COL1_Service,service_name);
        cvService.put(COL2_Service,contact_no);
        cvService.put(COL3_Service,service_location);
        long result =db.insert(Table_NAME_SERVICE,null,cvService);
        if(result==-1)
            return  false;
        else
            return true;
    }

    public Cursor getAllServices(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor serviceRes=db.rawQuery("select * from "+Table_NAME_SERVICE,null);
        return serviceRes;
    }

    public Integer delete_Service(String servID){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(Table_NAME_SERVICE,"SeviceID=?",new String[] {servID});

    }
    public boolean updateService(String Service_ID,String service_name,String contact_no,String service_location ){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cvServUPDT=new ContentValues();
        cvServUPDT.put(COL0_Service,Service_ID);
        cvServUPDT.put(COL1_Service,service_name);
        cvServUPDT.put(COL2_Service,contact_no);
        cvServUPDT.put(COL3_Service,service_location);
        db.update(Table_NAME_SERVICE,cvServUPDT, "SeviceID=?",new String[] {Service_ID});
        return true;

    }

    public Cursor getServiceBtLocation(String location){
        SQLiteDatabase db=this.getReadableDatabase();
        SQLiteQueryBuilder qbservice=new SQLiteQueryBuilder();
        String [] sqlSelect={COL0_Service,COL1_Service,COL2_Service,COL3_Service};
        qbservice.setTables(Table_NAME_SERVICE);

        Cursor cursor=qbservice.query(db,sqlSelect,"service_location LIKE ?",new String[]{"%"+location+"%"},null,null,null);
        /*List<service> result=new ArrayList<>();
        if(cursor.moveToNext()){
            do {
                service service=new service();
                service.setServiceId(cursor.getInt(cursor.getColumnIndex(COL0_Service)));
                service.setServiceName(cursor.getString(cursor.getColumnIndex(COL1_Service)));
                service.setService_contact(cursor.getString(cursor.getColumnIndex(COL2_Service)));
                service.setLocation(cursor.getString(cursor.getColumnIndex(COL3_Service)));
                result.add(service);
            }while (cursor.moveToNext());
        }
        return result;
        List<service>*/
        return cursor;

    }
    public Cursor getServiceDataUPD(String id){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor serviceRes=db.rawQuery("select * from "+Table_NAME_SERVICE+" where SeviceID="+id,null);
        return serviceRes;
    }
}
