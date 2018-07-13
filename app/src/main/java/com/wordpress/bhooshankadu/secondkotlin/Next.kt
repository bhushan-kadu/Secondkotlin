package com.wordpress.bhooshankadu.secondkotlin

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.next_activity.*
import java.io.FileInputStream
import java.io.InputStreamReader

class Next : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.next_activity)



        /* next_b1.setOnClickListener {

            var emp = Employee(next_et1.text.toString().toInt(),next_et2.text.toString(),next_et3.text.toString(),next_et4.text.toString())


            list.add(emp)

            var employees = Employees(list)

            var g = Gson()

            var json_resp = g.toJson(employees)

            var fos = openFileOutput("employees.json", Context.MODE_PRIVATE)

            fos.write(json_resp.toByteArray())

            fos.flush()
            fos.close()
        }*/


        next_b1.setOnClickListener({
            var g = Gson( )
            var emp = Employee(next_et1.text.toString().toInt(),
                    next_et2.text.toString(),next_et3.text.toString(),next_et4.text.toString())
            var list:MutableList<Employee>
            var fis:FileInputStream?
            try {
                fis = openFileInput("employees.json")
            }catch (e:Exception){
                fis=null
            }
            var emps:Employees
            if(fis==null){
                list = mutableListOf<Employee>()
                list.add(emp)
                emps= Employees(list)
            }else{
                emps =   g.fromJson(InputStreamReader(fis),
                        Employees::class.java)
                list = emps.employee
                list.add(emp)
            }


            var json_resp = g.toJson(emps)
            var fos =   openFileOutput("employees.json",
                    Context.MODE_PRIVATE)
            fos.write(json_resp.toByteArray())
            fos.flush()
            fos.close()
        })

       next_b2.setOnClickListener({
            var fis =  openFileInput("employees.json")
            var g = Gson( )
            var emps =   g.fromJson(InputStreamReader(fis),
                    Employees::class.java)
            var list = emps.employee
            var temp_list =   mutableListOf<String>()
            for (emp in list){
                temp_list.add(emp.id.toString() +"|" +
                        emp.name+"|"+emp.desg+"|"+emp.dept)
            }
            var myAdapter = ArrayAdapter<String>(
                    this@Next,
                    android.R.layout.simple_list_item_single_choice,
                    temp_list)
            next_lv.adapter = myAdapter
        })

    }

    }
