package com.muratguzel.usingdatastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref(var context: Context) {
    val Context.ds  : DataStore <Preferences> by preferencesDataStore("bilgiler")

    companion object{
        val NAME_KEY = stringPreferencesKey("NAME")
        val AGE_KEY = intPreferencesKey("AGE")
        val HEIGHT_KEY = doublePreferencesKey("HEIGHT")
        val SINGLE_KEY = booleanPreferencesKey("SINGLE")
        val FRIEND_LIST_KEY = stringSetPreferencesKey("FRIEND_LIST")
        val COUNTER_KEY = intPreferencesKey("COUNTER")
    }

    suspend fun saveName(name:String){
        context.ds.edit {
            it[NAME_KEY] = name
        }
    }
    suspend fun getName():String{
        val p = context.ds.data.first()
        return p[NAME_KEY] ?: "No Name"
    }
    suspend fun deleteName(){
        context.ds.edit {
            it.remove(NAME_KEY)
        }
    }
    suspend fun saveAge(age:Int){
        context.ds.edit {
            it[AGE_KEY] = age
        }
    }
    suspend fun getAge():Int{
        val p = context.ds.data.first()
        return p[AGE_KEY] ?: 0
    }

    suspend fun saveHeight(height:Double){
        context.ds.edit {
            it[HEIGHT_KEY] = height
        }
    }
    suspend fun getHeight():Double{
        val p = context.ds.data.first()
        return p[HEIGHT_KEY] ?: 0.0
    }

    suspend fun saveSingle(single:Boolean){
        context.ds.edit {
            it[SINGLE_KEY] = single
        }
    }
    suspend fun getSingle():Boolean{
        val p = context.ds.data.first()
        return p[SINGLE_KEY] ?: false
    }
    suspend fun saveFriendList(list :Set<String>){
        context.ds.edit {
            it[FRIEND_LIST_KEY] = list
        }
    }
    suspend fun getFriendList():Set<String>?{
        val p = context.ds.data.first()
        return p[FRIEND_LIST_KEY]
    }

    suspend fun saveCounter(counter:Int){
        context.ds.edit {
            it[COUNTER_KEY] = counter
        }
    }
    suspend fun getCounter():Int?{
        val p = context.ds.data.first()
        return p[COUNTER_KEY]
    }
    suspend fun deleteCounter(){
        context.ds.edit {
            it.remove(COUNTER_KEY)
        }
    }
}