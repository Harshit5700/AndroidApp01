package com.example.myapplication01

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var addTaskButton: FloatingActionButton

    private lateinit var tasks: ArrayList<String>
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerTasks)
        addTaskButton = findViewById(R.id.addTaskBtn)

        tasks = ArrayList()
        adapter = TaskAdapter(tasks)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        addTaskButton.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun showAddTaskDialog() {
        val input = EditText(this)
        input.hint = "Enter task"

        AlertDialog.Builder(this)
            .setTitle("Add Task")
            .setView(input)
            .setPositiveButton("Add") { _, _ ->
                val task = input.text.toString()
                if (task.isNotEmpty()) {
                    tasks.add(task)
                    adapter.notifyItemInserted(tasks.size - 1)
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
