package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Alumno;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db.UdepDBHelper;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db.tables.AlumnoTable;

/**
 * Created by ali on 23/09/18.
 */

public class DAOSQLAlumno {
    // https://pastebin.com/JFPqazvk
    private UdepDBHelper dbHelper;

    public DAOSQLAlumno(Context context) {
        this.dbHelper = new UdepDBHelper(context);
    }

    public void save(Alumno alumno) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        // writableDatabase.execSQL("INSERT INTO PRODUCT VALUES('NOMBRE', 'url', '0', '0')");

        ContentValues values = new ContentValues();
        values.put(AlumnoTable.COLUMN_DNI, alumno.getDNI());
        values.put(AlumnoTable.COLUMN_CARNE, alumno.getNro_carne());
        values.put(AlumnoTable.COLUMN_PHOTO, alumno.getFoto());
        values.put(AlumnoTable.COLUMN_NOMBRE, alumno.getNombre());
        values.put(AlumnoTable.COLUMN_APELLIDOS, alumno.getApellidos());
        values.put(AlumnoTable.COLUMN_DIRECCION, alumno.getDireccion());
        values.put(AlumnoTable.COLUMN_TELEFONO, alumno.getTelefono());
        values.put(AlumnoTable.COLUMN_EMAIL, alumno.getEmail());
        values.put(AlumnoTable.COLUMN_GENERO, alumno.getGenero());

        if (alumno.getId() == -1) {
            db.insert(AlumnoTable.TABLE_NAME, null, values);
        } else {
            db.update(AlumnoTable.TABLE_NAME, values,
                    AlumnoTable.COLUMN_ID + " = ?",
                    new String[]{ String.valueOf(alumno.getId()) });
        }
        db.close();
    }

    public List<Alumno> all() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(AlumnoTable.TABLE_NAME, new String[]{
                        AlumnoTable.COLUMN_ID,
                        AlumnoTable.COLUMN_DNI,
                        AlumnoTable.COLUMN_CARNE,
                        AlumnoTable.COLUMN_PHOTO,
                        AlumnoTable.COLUMN_NOMBRE,
                        AlumnoTable.COLUMN_APELLIDOS,
                        AlumnoTable.COLUMN_DIRECCION,
                        AlumnoTable.COLUMN_TELEFONO,
                        AlumnoTable.COLUMN_EMAIL,
                        AlumnoTable.COLUMN_GENERO
                },
                null, null, null, null, null);

        List<Alumno> alumnos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Alumno alumno = cursorToAlumno(cursor);
            alumnos.add(alumno);
        }
        cursor.close();
        db.close();

        return alumnos;
    }

    public Alumno cursorToAlumno(Cursor cursor) {
        Alumno alumno = new Alumno();
        alumno.setId(cursor.getInt(cursor.getColumnIndex(AlumnoTable.COLUMN_ID)));
        alumno.setDNI(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_DNI)));
        alumno.setNro_carne(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_CARNE)));
        alumno.setFoto(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_PHOTO)));
        alumno.setNombre(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_NOMBRE)));
        alumno.setApellidos(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_APELLIDOS)));
        alumno.setDireccion(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_DIRECCION)));
        alumno.setTelefono(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_TELEFONO)));
        alumno.setEmail(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_EMAIL)));
        alumno.setGenero(cursor.getString(cursor.getColumnIndex(AlumnoTable.COLUMN_GENERO)));
        return alumno;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        db.delete(AlumnoTable.TABLE_NAME,
                AlumnoTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public Alumno get(int id) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(AlumnoTable.TABLE_NAME, new String[]{
                        AlumnoTable.COLUMN_ID,
                        AlumnoTable.COLUMN_DNI,
                        AlumnoTable.COLUMN_CARNE,
                        AlumnoTable.COLUMN_PHOTO,
                        AlumnoTable.COLUMN_NOMBRE,
                        AlumnoTable.COLUMN_APELLIDOS,
                        AlumnoTable.COLUMN_DIRECCION,
                        AlumnoTable.COLUMN_TELEFONO,
                        AlumnoTable.COLUMN_EMAIL,
                        AlumnoTable.COLUMN_GENERO
                },
                AlumnoTable.COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        while (cursor.moveToNext()) {
            return cursorToAlumno(cursor);
        }
        cursor.close();
        db.close();
        return null;
    }

    public Alumno getByCarne(String carne) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(AlumnoTable.TABLE_NAME, new String[]{
                        AlumnoTable.COLUMN_ID,
                        AlumnoTable.COLUMN_DNI,
                        AlumnoTable.COLUMN_CARNE,
                        AlumnoTable.COLUMN_PHOTO,
                        AlumnoTable.COLUMN_NOMBRE,
                        AlumnoTable.COLUMN_APELLIDOS,
                        AlumnoTable.COLUMN_DIRECCION,
                        AlumnoTable.COLUMN_TELEFONO,
                        AlumnoTable.COLUMN_EMAIL,
                        AlumnoTable.COLUMN_GENERO
                },
                AlumnoTable.COLUMN_CARNE + " = ?", new String[]{String.valueOf(carne)}, null, null, null);

        while (cursor.moveToNext()) {
            return cursorToAlumno(cursor);
        }
        cursor.close();
        db.close();
        return null;
    }

    public Alumno getLogin(String usuario, String password) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        String whereClause = AlumnoTable.COLUMN_CARNE + "=? AND " + AlumnoTable.COLUMN_CARNE +"=?";
        String[] whereArgs = new String[]{usuario, password};

        Cursor cursor = db.query(AlumnoTable.TABLE_NAME, new String[]{
                        AlumnoTable.COLUMN_ID,
                        AlumnoTable.COLUMN_DNI,
                        AlumnoTable.COLUMN_CARNE,
                        AlumnoTable.COLUMN_PHOTO,
                        AlumnoTable.COLUMN_NOMBRE,
                        AlumnoTable.COLUMN_APELLIDOS,
                        AlumnoTable.COLUMN_DIRECCION,
                        AlumnoTable.COLUMN_TELEFONO,
                        AlumnoTable.COLUMN_EMAIL,
                        AlumnoTable.COLUMN_GENERO
                },
                whereClause, whereArgs, null, null, null);

        while (cursor.moveToNext()) {
            return cursorToAlumno(cursor);
        }
        cursor.close();
        db.close();
        return null;
    }
}
