package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db.UdepDBHelper;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db.tables.CursoTable;

public class DAOSQLCurso {
    private UdepDBHelper dbHelper;

    public DAOSQLCurso(Context context) {
        this.dbHelper = new UdepDBHelper(context);
    }

    public void save(Curso curso) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        // writableDatabase.execSQL("INSERT INTO ORDER VALUES('', '', '', '')");

        ContentValues values = new ContentValues();
        values.put(CursoTable.COLUMN_CODIGO, curso.getCodigo());
        values.put(CursoTable.COLUMN_NOMBRE, curso.getNombre());
        values.put(CursoTable.COLUMN_CICLO, curso.getCiclo());
        values.put(CursoTable.COLUMN_CREDITOS, curso.getCreditos());
        values.put(CursoTable.COLUMN_PROFESOR, curso.getProfesor());
        values.put(CursoTable.COLUMN_OBSERVACIONES, curso.getObservaciones());
        values.put(CursoTable.COLUMN_NOTA, curso.getNota());

        if (curso.getId() == -1) {
            db.insert(CursoTable.TABLE_NAME, null, values);
        } else {
            db.update(CursoTable.TABLE_NAME, values,
                    CursoTable.COLUMN_ID + " = ?",
                    new String[]{ String.valueOf(curso.getId()) });
        }
        db.close();
    }

    public List<Curso> all() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(CursoTable.TABLE_NAME, new String[]{
                        CursoTable.COLUMN_ID,
                        CursoTable.COLUMN_CODIGO,
                        CursoTable.COLUMN_NOMBRE,
                        CursoTable.COLUMN_CICLO,
                        CursoTable.COLUMN_CREDITOS,
                        CursoTable.COLUMN_PROFESOR,
                        CursoTable.COLUMN_OBSERVACIONES,
                        CursoTable.COLUMN_NOTA
                },
                null, null, null, null, null);

        List<Curso> cursos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Curso curso = cursorToCurso(cursor);
            cursos.add(curso);
        }
        cursor.close();
        db.close();

        return cursos;
    }

    public List<Curso> allForMatricula() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(CursoTable.TABLE_NAME, new String[]{
                        CursoTable.COLUMN_ID,
                        CursoTable.COLUMN_CODIGO,
                        CursoTable.COLUMN_NOMBRE,
                        CursoTable.COLUMN_CICLO,
                        CursoTable.COLUMN_CREDITOS,
                        CursoTable.COLUMN_PROFESOR,
                        CursoTable.COLUMN_OBSERVACIONES,
                        CursoTable.COLUMN_NOTA
                },
                CursoTable.COLUMN_CICLO + " IN (?, ?, ?)", new String[]{ "08", "09", "10" }, null, null, null);

        List<Curso> cursos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Curso curso = cursorToCurso(cursor);
            cursos.add(curso);
        }
        cursor.close();
        db.close();

        return cursos;
    }

    public List<Curso> getByCiclo(String ciclo) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(CursoTable.TABLE_NAME, new String[]{
                        CursoTable.COLUMN_ID,
                        CursoTable.COLUMN_CODIGO,
                        CursoTable.COLUMN_NOMBRE,
                        CursoTable.COLUMN_CICLO,
                        CursoTable.COLUMN_CREDITOS,
                        CursoTable.COLUMN_PROFESOR,
                        CursoTable.COLUMN_OBSERVACIONES,
                        CursoTable.COLUMN_NOTA
                },
                CursoTable.COLUMN_CICLO + "= ?", new String[]{ ciclo }, null, null, null);

        List<Curso> cursos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Curso curso = cursorToCurso(cursor);
            cursos.add(curso);
        }
        cursor.close();
        db.close();

        return cursos;
    }

    public Curso cursorToCurso(Cursor cursor) {
        Curso curso = new Curso();
        curso.setId(cursor.getInt(cursor.getColumnIndex(CursoTable.COLUMN_ID)));
        curso.setCodigo(cursor.getString(cursor.getColumnIndex(CursoTable.COLUMN_CODIGO)));
        curso.setNombre(cursor.getString(cursor.getColumnIndex(CursoTable.COLUMN_NOMBRE)));
        curso.setCiclo(cursor.getString(cursor.getColumnIndex(CursoTable.COLUMN_CICLO)));
        curso.setCreditos(cursor.getInt(cursor.getColumnIndex(CursoTable.COLUMN_CREDITOS)));
        curso.setProfesor(cursor.getString(cursor.getColumnIndex(CursoTable.COLUMN_PROFESOR)));
        curso.setObservaciones(cursor.getString(cursor.getColumnIndex(CursoTable.COLUMN_OBSERVACIONES)));
        curso.setNota(cursor.getInt(cursor.getColumnIndex(CursoTable.COLUMN_NOTA)));
        return curso;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        db.delete(CursoTable.TABLE_NAME,
                CursoTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public Curso get(int id) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(CursoTable.TABLE_NAME, new String[]{
                        CursoTable.COLUMN_ID,
                        CursoTable.COLUMN_CODIGO,
                        CursoTable.COLUMN_NOMBRE,
                        CursoTable.COLUMN_CICLO,
                        CursoTable.COLUMN_CREDITOS,
                        CursoTable.COLUMN_PROFESOR,
                        CursoTable.COLUMN_OBSERVACIONES,
                        CursoTable.COLUMN_NOTA
                },
                CursoTable.COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        while (cursor.moveToNext()) {
            return cursorToCurso(cursor);
        }
        cursor.close();
        db.close();
        return null;
    }
}
