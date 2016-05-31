package com.sjtu.charles.data.troop.remote;

import android.support.annotation.NonNull;

import com.sjtu.charles.data.troop.Troop;
import com.sjtu.charles.data.troop.TroopsDataSource;

/**
 * Created by charles on 2016/5/27.
 */
public class TroopsRemoteDataSource implements TroopsDataSource {

    private static TroopsRemoteDataSource INSTANCE;

    public static TroopsRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TroopsRemoteDataSource();
        }
        return INSTANCE;
    }

    private TroopsRemoteDataSource() {}


    @Override
    public void getTroops(@NonNull LoadTroopCallback callback) {

    }

    @Override
    public void getTroop(@NonNull String troopId, @NonNull GetTroopCallback callback) {

    }

    @Override
    public void saveTroop(@NonNull Troop troop) {

    }

    @Override
    public void completeTroop(@NonNull Troop troop) {

    }

    @Override
    public void completeTroop(@NonNull String troopId) {

    }

    @Override
    public void activateTroop(@NonNull Troop troop) {

    }

    @Override
    public void activateTroop(@NonNull String troopId) {

    }

    @Override
    public void clearCompletedTroops() {

    }

    @Override
    public void refreshTroops() {

    }

    @Override
    public void deleteAllTroops() {

    }

    @Override
    public void deleteTroop(@NonNull String troopId) {

    }
}
