package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db.tables.*;

public class UdepDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "udepbetamovil.db";

    private static final String SQL_CREATE_ALUMNO =
            "CREATE TABLE " + AlumnoTable.TABLE_NAME + " (" +
                    AlumnoTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AlumnoTable.COLUMN_DNI + " TEXT unique NOT NULL, " +
                    AlumnoTable.COLUMN_CARNE + " TEXT unique NOT NULL, " +
                    AlumnoTable.COLUMN_PHOTO + " TEXT unique NOT NULL, " +
                    AlumnoTable.COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    AlumnoTable.COLUMN_APELLIDOS + " TEXT NOT NULL, " +
                    AlumnoTable.COLUMN_DIRECCION + " TEXT, " +
                    AlumnoTable.COLUMN_TELEFONO + " TEXT, " +
                    AlumnoTable.COLUMN_EMAIL + " TEXT, " +
                    AlumnoTable.COLUMN_GENERO + " TEXT )";

    private static final String SQL_CREATE_CURSO =
            "CREATE TABLE " + CursoTable.TABLE_NAME + " (" +
                    CursoTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CursoTable.COLUMN_CODIGO + " TEXT unique NOT NULL, " +
                    CursoTable.COLUMN_NOMBRE + " TEXT NOT NULL, " +
                    CursoTable.COLUMN_CICLO + " TEXT NOT NULL, " +
                    CursoTable.COLUMN_CREDITOS + " INTEGER NOT NULL, " +
                    CursoTable.COLUMN_PROFESOR + " TEXT, " +
                    CursoTable.COLUMN_OBSERVACIONES + " TEXT, " +
                    CursoTable.COLUMN_NOTA + " INTEGER )";

    private static final String SQL_CREATE_MATRICULA =
            "CREATE TABLE " + MatriculaTable.TABLE_NAME + " (" +
                    MatriculaTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    MatriculaTable.COLUMN_ID_CURSO + " INTEGER NOT NULL, " +
                    MatriculaTable.COLUMN_ID_ALUMNO + " INTEGER NOT NULL, " +
                    MatriculaTable.COLUMN_FECHA + " DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                    MatriculaTable.COLUMN_SEMESTRE + " TEXT NOT NULL, " +
                    MatriculaTable.COLUMN_NOTA + " INTEGER, " +
                    " FOREIGN KEY(" + MatriculaTable.COLUMN_ID_CURSO + ") REFERENCES " + CursoTable.TABLE_NAME + "(" + CursoTable.COLUMN_ID + ")," +
                    " FOREIGN KEY(" + MatriculaTable.COLUMN_ID_ALUMNO + ") REFERENCES " + AlumnoTable.TABLE_NAME + "(" + AlumnoTable.COLUMN_ID + "));";

    public UdepDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL(SQL_CREATE_CLIENT);
        db.execSQL(SQL_CREATE_ALUMNO);
        db.execSQL(SQL_CREATE_CURSO);
        db.execSQL(SQL_CREATE_MATRICULA);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("DROP TABLE " + ClientTable.TABLE_NAME);
        db.execSQL("DROP TABLE " + AlumnoTable.TABLE_NAME);
        db.execSQL("DROP TABLE " + CursoTable.TABLE_NAME);
        db.execSQL("DROP TABLE " + MatriculaTable.TABLE_NAME);
        this.onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}