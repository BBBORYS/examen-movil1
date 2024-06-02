package com.borys.moviles_examen.data.evento.interfaces;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.borys.moviles_examen.data.evento.Evento;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class EventoDao_Impl implements EventoDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Evento> __insertionAdapterOfEvento;

  private final EntityDeletionOrUpdateAdapter<Evento> __deletionAdapterOfEvento;

  private final EntityDeletionOrUpdateAdapter<Evento> __updateAdapterOfEvento;

  public EventoDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfEvento = new EntityInsertionAdapter<Evento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Evento` (`nombre`,`email`,`direccion`,`telefono`,`imagenId`,`eventoID`) VALUES (?,?,?,?,?,nullif(?, 0))";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Evento entity) {
        statement.bindString(1, entity.getNombre());
        statement.bindString(2, entity.getEmail());
        statement.bindString(3, entity.getDireccion());
        statement.bindString(4, entity.getTelefono());
        statement.bindLong(5, entity.getImagenId());
        statement.bindLong(6, entity.getEventoID());
      }
    };
    this.__deletionAdapterOfEvento = new EntityDeletionOrUpdateAdapter<Evento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Evento` WHERE `eventoID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Evento entity) {
        statement.bindLong(1, entity.getEventoID());
      }
    };
    this.__updateAdapterOfEvento = new EntityDeletionOrUpdateAdapter<Evento>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Evento` SET `nombre` = ?,`email` = ?,`direccion` = ?,`telefono` = ?,`imagenId` = ?,`eventoID` = ? WHERE `eventoID` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Evento entity) {
        statement.bindString(1, entity.getNombre());
        statement.bindString(2, entity.getEmail());
        statement.bindString(3, entity.getDireccion());
        statement.bindString(4, entity.getTelefono());
        statement.bindLong(5, entity.getImagenId());
        statement.bindLong(6, entity.getEventoID());
        statement.bindLong(7, entity.getEventoID());
      }
    };
  }

  @Override
  public void agregarEvento(final Evento evento) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfEvento.insert(evento);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public Object delete(final Evento evento, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfEvento.handle(evento);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Evento evento, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfEvento.handle(evento);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Evento obtenerEventoPorId(final int id) {
    final String _sql = "SELECT * FROM Evento WHERE eventoID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
      final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
      final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "direccion");
      final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "telefono");
      final int _cursorIndexOfImagenId = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenId");
      final int _cursorIndexOfEventoID = CursorUtil.getColumnIndexOrThrow(_cursor, "eventoID");
      final Evento _result;
      if (_cursor.moveToFirst()) {
        final String _tmpNombre;
        _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
        final String _tmpEmail;
        _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
        final String _tmpDireccion;
        _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
        final String _tmpTelefono;
        _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
        final int _tmpImagenId;
        _tmpImagenId = _cursor.getInt(_cursorIndexOfImagenId);
        _result = new Evento(_tmpNombre,_tmpEmail,_tmpDireccion,_tmpTelefono,_tmpImagenId);
        final int _tmpEventoID;
        _tmpEventoID = _cursor.getInt(_cursorIndexOfEventoID);
        _result.setEventoID(_tmpEventoID);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Flow<List<Evento>> obtenerTodosLosEvento() {
    final String _sql = "SELECT * FROM Evento";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"Evento"}, new Callable<List<Evento>>() {
      @Override
      @NonNull
      public List<Evento> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfNombre = CursorUtil.getColumnIndexOrThrow(_cursor, "nombre");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfDireccion = CursorUtil.getColumnIndexOrThrow(_cursor, "direccion");
          final int _cursorIndexOfTelefono = CursorUtil.getColumnIndexOrThrow(_cursor, "telefono");
          final int _cursorIndexOfImagenId = CursorUtil.getColumnIndexOrThrow(_cursor, "imagenId");
          final int _cursorIndexOfEventoID = CursorUtil.getColumnIndexOrThrow(_cursor, "eventoID");
          final List<Evento> _result = new ArrayList<Evento>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Evento _item;
            final String _tmpNombre;
            _tmpNombre = _cursor.getString(_cursorIndexOfNombre);
            final String _tmpEmail;
            _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            final String _tmpDireccion;
            _tmpDireccion = _cursor.getString(_cursorIndexOfDireccion);
            final String _tmpTelefono;
            _tmpTelefono = _cursor.getString(_cursorIndexOfTelefono);
            final int _tmpImagenId;
            _tmpImagenId = _cursor.getInt(_cursorIndexOfImagenId);
            _item = new Evento(_tmpNombre,_tmpEmail,_tmpDireccion,_tmpTelefono,_tmpImagenId);
            final int _tmpEventoID;
            _tmpEventoID = _cursor.getInt(_cursorIndexOfEventoID);
            _item.setEventoID(_tmpEventoID);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
