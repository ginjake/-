package com.example.ginjake.kotlin_test.client

import android.content.ContentValues.TAG
import android.util.Log
import com.example.ginjake.kotlin_test.model.Article
import com.example.ginjake.kotlin_test.model.Version
import com.github.kittinunf.fuel.android.extension.responseJson
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import org.json.JSONArray
import org.json.JSONObject



/**
 * Created by ginjake on 2018/01/29.
 */
class UpdateClient()  {

    companion object {

        fun getVersion() {
            "http://www.ginjake.net/kotlinApi/version.php".httpGet().responseJson { request, response, result ->
                when (result) {
                // ステータスコード 2xx
                    is Result.Success -> {
                        val json = result.value.obj()
                        val results =json.get("results") as JSONArray
                        val version:Double = ((results[0] as JSONObject)["version"] as String).toDouble()

                        if (Version.update_check(version_num=version)) {
                            Version.create(version_num = version)
                            // TODO アップデート処理
                            Log.d("json", "アップデート")
                        } else {
                            Log.d("json", "既に最新")
                        }

                    }
                // ステータスコード 2xx以外
                    is Result.Failure -> {
                        // TODO エラー処理
                    }
                }

            }
        }
        fun getUpdate() {
            "http://www.ginjake.net/kotlinApi/create_json.php".httpGet().responseJson { request, response, result ->
                when (result) {
                // ステータスコード 2xx
                    is Result.Success -> {
                        val json = result.value.obj()
                        val results =json.get("results") as JSONArray
                        val version:Double = ((results[0] as JSONObject)["version"] as String).toDouble()
                        //result.forEach{

                        //}
                        //for (item in results){

//                        }
                        if (Version.update_check(version_num=version)) {
                            Version.create(version_num = version)
                            // TODO アップデート処理
                            Log.d("json", "アップデート")
                        } else {
                            Log.d("json", "既に最新")
                        }

                    }
                // ステータスコード 2xx以外
                    is Result.Failure -> {
                        // TODO エラー処理
                    }
                }

            }
        }
    }


}