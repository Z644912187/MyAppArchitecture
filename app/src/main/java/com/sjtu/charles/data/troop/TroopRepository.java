package com.sjtu.charles.data.troop;


import android.support.annotation.NonNull;

import java.util.Map;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * 关于组队的数据仓库
 * Created by zhuyifei on 2016/5/27.
 */
public class TroopRepository implements TroopsDataSource {

    private static TroopRepository INSTANCE = null;

    private final TroopsDataSource mTroopsRemoteDataSource;

    private final TroopsDataSource mTroopsLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Troop> mCachedTroops;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    private TroopRepository(@NonNull TroopsDataSource troopsRemoteDataSource,
                            @NonNull TroopsDataSource troopsLocalDataSource) {
        mTroopsRemoteDataSource = checkNotNull(troopsRemoteDataSource);
        mTroopsLocalDataSource = checkNotNull(troopsLocalDataSource);
    }


    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param tasksRemoteDataSource the backend data source
     * @param tasksLocalDataSource  the device storage data source
     * @return the {@link TroopRepository} instance
     */
    public static TroopRepository getInstance(TroopsDataSource tasksRemoteDataSource,
                                              TroopsDataSource tasksLocalDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new TroopRepository(tasksRemoteDataSource, tasksLocalDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(TroopsDataSource, TroopsDataSource)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }


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
