package movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Curso;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.Models.Matricula;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db.UdepDBHelper;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db.tables.CursoTable;
import movil.upao.android.aplicaciones.upao.edu.udepbetamovilt.db.tables.MatriculaTable;

public class DAOSQLMatricula {
    // https://pastebin.com/JFPqazvk
    private UdepDBHelper dbHelper;
    private DAOSQLCurso dao_curso;

    public DAOSQLMatricula(Context context, DAOSQLCurso dao_curso) {
        this.dbHelper = new UdepDBHelper(context);
        this.dao_curso = dao_curso;//new DAOSQLCurso(context);
    }

    public void save(Matricula matricula) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();
        // writableDatabase.execSQL("INSERT INTO DETORDER VALUES('', 'url', '0', '0')");

        ContentValues values = new ContentValues();
        values.put(MatriculaTable.COLUMN_ID_CURSO, matricula.getId_curso());
        values.put(MatriculaTable.COLUMN_ID_ALUMNO, matricula.getId_alumno());
        values.put(MatriculaTable.COLUMN_FECHA, matricula.getFecha());
        values.put(MatriculaTable.COLUMN_SEMESTRE, matricula.getSemestre());
        values.put(MatriculaTable.COLUMN_NOTA, matricula.getNota());

        if (matricula.getId() == -1) {
            db.insert(MatriculaTable.TABLE_NAME, null, values);
        } else {
            db.update(MatriculaTable.TABLE_NAME, values,
                    MatriculaTable.COLUMN_ID + " = ?",
                    new String[]{ String.valueOf(matricula.getId()) });
        }
        db.close();
    }

    public List<Matricula> all() {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MatriculaTable.TABLE_NAME, new String[]{
                        MatriculaTable.COLUMN_ID,
                        MatriculaTable.COLUMN_ID_CURSO,
                        MatriculaTable.COLUMN_ID_ALUMNO,
                        MatriculaTable.COLUMN_FECHA,
                        MatriculaTable.COLUMN_SEMESTRE,
                        MatriculaTable.COLUMN_NOTA
                },
                null, null, null, null, null);

        List<Matricula> matriculas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Matricula matricula = cursorToMatricula(cursor);
            matriculas.add(matricula);
        }
        cursor.close();
        db.close();

        return matriculas;
    }

    public List<Matricula> allByCurso(int id_order) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MatriculaTable.TABLE_NAME, new String[]{
                        MatriculaTable.COLUMN_ID,
                        MatriculaTable.COLUMN_ID_CURSO,
                        MatriculaTable.COLUMN_ID_ALUMNO,
                        MatriculaTable.COLUMN_FECHA,
                        MatriculaTable.COLUMN_SEMESTRE,
                        MatriculaTable.COLUMN_NOTA
                },
                MatriculaTable.COLUMN_ID_CURSO + " = ?", new String[]{String.valueOf(id_order)}, null, null, null);

        List<Matricula> matriculas = new ArrayList<>();
        while (cursor.moveToNext()) {
            Matricula matricula = cursorToMatricula(cursor);
            matriculas.add(matricula);
        }
        cursor.close();
        db.close();

        return matriculas;
    }

    public Matricula cursorToMatricula(Cursor cursor) {
        Matricula matricula = new Matricula();
        matricula.setId(cursor.getInt(cursor.getColumnIndex(MatriculaTable.COLUMN_ID)));
        matricula.setId_curso(cursor.getInt(cursor.getColumnIndex(MatriculaTable.COLUMN_ID_CURSO)));
        matricula.setId_alumno(cursor.getInt(cursor.getColumnIndex(MatriculaTable.COLUMN_ID_ALUMNO)));
        matricula.setFecha(cursor.getString(cursor.getColumnIndex(MatriculaTable.COLUMN_FECHA)));
        matricula.setSemestre(cursor.getString(cursor.getColumnIndex(MatriculaTable.COLUMN_SEMESTRE)));
        matricula.setNota(cursor.getInt(cursor.getColumnIndex(MatriculaTable.COLUMN_NOTA)));
        return matricula;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        db.delete(MatriculaTable.TABLE_NAME,
                MatriculaTable.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAll() {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        db.delete(MatriculaTable.TABLE_NAME,null,null);
        db.close();
    }

    public void deleteByCurso(int id_order) {
        SQLiteDatabase db = this.dbHelper.getWritableDatabase();

        db.delete(MatriculaTable.TABLE_NAME,
                MatriculaTable.COLUMN_ID_CURSO + " = ?",
                new String[]{String.valueOf(id_order)});
        db.close();
    }

    public Matricula get(int id) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MatriculaTable.TABLE_NAME, new String[]{
                        MatriculaTable.COLUMN_ID,
                        MatriculaTable.COLUMN_ID_CURSO,
                        MatriculaTable.COLUMN_ID_ALUMNO,
                        MatriculaTable.COLUMN_FECHA,
                        MatriculaTable.COLUMN_SEMESTRE,
                        MatriculaTable.COLUMN_NOTA
                },
                MatriculaTable.COLUMN_ID + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        while (cursor.moveToNext()) {
            return cursorToMatricula(cursor);
        }
        cursor.close();
        db.close();
        return null;
    }

    public Matricula getByOtherColumn(int id, String column) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        Cursor cursor = db.query(MatriculaTable.TABLE_NAME, new String[]{
                        MatriculaTable.COLUMN_ID,
                        MatriculaTable.COLUMN_ID_CURSO,
                        MatriculaTable.COLUMN_ID_ALUMNO,
                        MatriculaTable.COLUMN_FECHA,
                        MatriculaTable.COLUMN_SEMESTRE,
                        MatriculaTable.COLUMN_NOTA
                },
                column + " = ?", new String[]{String.valueOf(id)}, null, null, null);

        while (cursor.moveToNext()) {
            return cursorToMatricula(cursor);
        }
        cursor.close();
        db.close();
        return null;
    }

    public List<Curso> allCursosByAlumno(int id_alumno) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        String t_curso = CursoTable.TABLE_NAME;
        String t_matricula = MatriculaTable.TABLE_NAME;

        String query = "SELECT "+
                t_curso + "." + CursoTable.COLUMN_ID + ", "+
                t_curso + "." + CursoTable.COLUMN_CODIGO + ", "+
                t_curso + "." + CursoTable.COLUMN_NOMBRE + ", "+
                t_curso + "." + CursoTable.COLUMN_CICLO + ", "+
                t_curso + "." + CursoTable.COLUMN_CREDITOS + ", "+
                t_curso + "." + CursoTable.COLUMN_PROFESOR + ", "+
                t_curso + "." + CursoTable.COLUMN_OBSERVACIONES + ", "+
                t_curso + "." + CursoTable.COLUMN_NOTA + " "+
                " FROM " + t_curso +
                " JOIN " + t_matricula + " ON " + t_curso + "." + CursoTable.COLUMN_ID + "=" + t_matricula + "." + MatriculaTable.COLUMN_ID_CURSO +
                " WHERE "+ t_matricula + "." + MatriculaTable.COLUMN_ID_ALUMNO + "=" + id_alumno;

        Cursor cursor = db.rawQuery(query, null);

        List<Curso> cursos = new ArrayList<>();
        while (cursor.moveToNext()) {
            Curso curso = dao_curso.cursorToCurso(cursor);
            cursos.add(curso);
        }
        cursor.close();
        db.close();

        return cursos;
    }
    /*

    public List<Product> allProductsByOrder(int id_order) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        String t_curso = ProductTable.TABLE_NAME;
        String t_matricula = MatriculaTable.TABLE_NAME;

        String query = "SELECT "+
                t_curso + "." + ProductTable.COLUMN_ID + ", "+
                t_curso + "." + ProductTable.COLUMN_NAME + ", "+
                t_curso + "." + ProductTable.COLUMN_PHOTO_URL + ", "+
                t_matricula + "." + MatriculaTable.COLUMN_QUANTITY + " AS '" + ProductTable.COLUMN_QUANTITY+ "', "+
                t_curso + "." + ProductTable.COLUMN_FEATURED + ", "+
                t_curso + "." + ProductTable.COLUMN_PRICE + " * " + t_matricula + "." + MatriculaTable.COLUMN_PRICE + " AS '" + ProductTable.COLUMN_PRICE+ "' "+
                " FROM " + t_curso +
                " JOIN " + t_matricula + " ON " + t_curso + "." + ProductTable.COLUMN_ID + "=" + t_matricula + "." + MatriculaTable.COLUMN_ID_PRODUCT +
                " WHERE "+ t_matricula + "." + MatriculaTable.COLUMN_ID_ORDER + "=" + id_order;

        Cursor cursor = db.rawQuery(query, null);

        List<Product> products = new ArrayList<>();
        while (cursor.moveToNext()) {
            Product product = cursorToProduct(cursor);
            products.add(product);
        }
        cursor.close();
        db.close();

        return products;
    }

    public Matricula existeAlumnoInCurso(int id_order, int id_product) {
        SQLiteDatabase db = this.dbHelper.getReadableDatabase();

        String t_curso = ProductTable.TABLE_NAME;
        String t_matricula = MatriculaTable.TABLE_NAME;
        String t_order = OrderTable.TABLE_NAME;

        String query = "SELECT "+
                t_matricula + "." + MatriculaTable.COLUMN_ID + ", "+
                t_matricula + "." + MatriculaTable.COLUMN_ID_ORDER + ", "+
                t_matricula + "." + MatriculaTable.COLUMN_ID_PRODUCT + ", "+
                t_matricula + "." + MatriculaTable.COLUMN_QUANTITY + ", "+
                t_matricula + "." + MatriculaTable.COLUMN_PRICE + " "+
                " FROM " + t_matricula +
                " JOIN " + t_curso + " ON " +  t_matricula + "." + MatriculaTable.COLUMN_ID_PRODUCT + "=" + t_curso + "." + ProductTable.COLUMN_ID +
                " JOIN " + t_order + " ON " +  t_matricula + "." + MatriculaTable.COLUMN_ID_ORDER + "=" + t_order + "." + OrderTable.COLUMN_ID +
                " WHERE "+ t_matricula + "." + MatriculaTable.COLUMN_ID_ORDER + "=" + id_order +
                " AND "+ t_matricula + "." + MatriculaTable.COLUMN_ID_PRODUCT + "=" + id_product;

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            return cursorToMatricula(cursor);
        }
        cursor.close();
        db.close();
        return null;
    }
    */
}