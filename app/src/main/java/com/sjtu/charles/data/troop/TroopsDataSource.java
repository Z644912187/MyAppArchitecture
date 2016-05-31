package com.sjtu.charles.data.troop;

import android.support.annotation.NonNull;

import java.util.List;

/**
 * 组队mode层
 * Created by zhuyifei on 2016/5/27.
 */
public interface TroopsDataSource {

    interface LoadTroopCallback {
        void onTroopLoaded(List<Troop> troops);
        void onDataNotAvaiable();
    }

    interface GetTroopCallback {
        void onTroopLoaded(Troop troop);
        void onDataNotAvaiable();
    }

    void getTroops(@NonNull LoadTroopCallback callback);

    void getTroop(@NonNull String troopId, @NonNull GetTroopCallback callback);

    void saveTroop(@NonNull Troop troop);

    void completeTroop(@NonNull Troop troop);

    void completeTroop(@NonNull String troopId);

    void activateTroop(@NonNull Troop troop);

    void activateTroop(@NonNull String troopId);

    void clearCompletedTroops();

    void refreshTroops();

    void deleteAllTroops();

    void deleteTroop(@NonNull String troopId);

}
