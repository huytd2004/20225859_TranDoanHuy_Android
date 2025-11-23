package com.example.student_management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// Model dữ liệu sinh viên (gồm tên và MSSV)
data class StudentModel(var name: String, var mssv: String)

class MainActivity : AppCompatActivity() {

    private lateinit var edtMSSV: EditText
    private lateinit var edtName: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var listView: ListView

    // Danh sách lưu sinh viên
    private val studentList = mutableListOf<StudentModel>()

    // Adapter custom để hiển thị lên ListView
    private lateinit var adapter: StudentAdapter

    // Lưu vị trí sinh viên đang được chọn (để cập nhật)
    // -1 nghĩa là chưa chọn ai
    private var selectedPosition = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ View từ layout
        edtMSSV = findViewById(R.id.editTextMSSV)
        edtName = findViewById(R.id.editTextName)
        btnAdd = findViewById(R.id.buttonAdd)
        btnUpdate = findViewById(R.id.buttonUpdate)
        listView = findViewById(R.id.listViewStudents)

        // Thêm dữ liệu mẫu ban đầu
        studentList.add(StudentModel("Nguyễn Văn A", "20200001"))
        studentList.add(StudentModel("Trần Thị B", "20200002"))
        studentList.add(StudentModel("Lê Văn C", "20200003"))
        studentList.add(StudentModel("Phạm Thị D", "20200004"))
        studentList.add(StudentModel("Hoàng Văn E", "20200005"))

        // Khởi tạo Adapter
        // Truyền callback khi bấm nút XÓA trong mỗi item
        adapter = StudentAdapter(this, studentList) { position ->
            deleteStudent(position)
        }
        listView.adapter = adapter

        // -----------------------------
        // 1. Xử lý nút Add (Thêm)
        // -----------------------------
        btnAdd.setOnClickListener {
            val mssv = edtMSSV.text.toString().trim()
            val name = edtName.text.toString().trim()

            // Kiểm tra nhập đủ thông tin
            if (mssv.isNotEmpty() && name.isNotEmpty()) {
                // Thêm vào danh sách
                studentList.add(StudentModel(name, mssv))
                adapter.notifyDataSetChanged()  // Cập nhật ListView

                clearInput()  // Reset form
                Toast.makeText(this, "Đã thêm sinh viên", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show()
            }
        }

        // ------------------------------------------------------
        // 2. Xử lý sự kiện khi click vào 1 dòng trong ListView
        // ------------------------------------------------------
        listView.setOnItemClickListener { _, _, position, _ ->
            val student = studentList[position]

            // Đổ dữ liệu lên các EditText để cho người dùng sửa
            edtMSSV.setText(student.mssv)
            edtName.setText(student.name)

            // Lưu vị trí dòng đang được chọn
            selectedPosition = position

            Toast.makeText(this, "Đang chọn: ${student.name}", Toast.LENGTH_SHORT).show()

            // Nếu muốn MSSV không được sửa thì thêm:
            // edtMSSV.isEnabled = false
        }

        // -----------------------------
        // 3. Xử lý nút Update (Cập nhật)
        // -----------------------------
        btnUpdate.setOnClickListener {

            // Không chọn item mà bấm Update → báo lỗi
            if (selectedPosition == -1) {
                Toast.makeText(this, "Vui lòng chọn sinh viên để cập nhật", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val mssv = edtMSSV.text.toString().trim()
            val name = edtName.text.toString().trim()

            // Kiểm tra dữ liệu hợp lệ
            if (mssv.isNotEmpty() && name.isNotEmpty()) {

                // Ghi đè lại dữ liệu tại vị trí đã chọn
                studentList[selectedPosition].name = name
                studentList[selectedPosition].mssv = mssv

                adapter.notifyDataSetChanged()  // Refresh ListView

                clearInput()
                selectedPosition = -1  // Reset trạng thái

                Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Thông tin không được để trống", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ------------------------
    // Hàm xóa sinh viên
    // ------------------------
    private fun deleteStudent(position: Int) {

        // Nếu đang sửa chính sinh viên này → reset form
        if (position == selectedPosition) {
            clearInput()
            selectedPosition = -1
        }

        // Nếu xóa dòng phía trên dòng đang chọn → selectedPosition giảm đi 1
        if (position < selectedPosition) {
            selectedPosition--
        }

        // Xóa khỏi danh sách
        studentList.removeAt(position)
        adapter.notifyDataSetChanged()

        Toast.makeText(this, "Đã xóa", Toast.LENGTH_SHORT).show()
    }

    // Xóa text và focus vào MSSV
    private fun clearInput() {
        edtMSSV.setText("")
        edtName.setText("")
        edtMSSV.requestFocus()
    }
}

// ===================================================================
// ADAPTER CUSTOM CHO LISTVIEW
// ===================================================================
class StudentAdapter(
    private val context: AppCompatActivity,
    private val list: List<StudentModel>,
    private val onDeleteClick: (Int) -> Unit   // Callback khi bấm nút xóa
) : BaseAdapter() {

    // Tổng số item
    override fun getCount(): Int = list.size

    // Lấy item theo vị trí
    override fun getItem(position: Int): Any = list[position]

    // ItemId (ở đây dùng luôn position)
    override fun getItemId(position: Int): Long = position.toLong()

    // Hàm render UI cho từng dòng của ListView
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        // Dùng ViewHolder để tái sử dụng view → tăng hiệu năng
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
            holder = ViewHolder()
            holder.tvName = view.findViewById(R.id.tvName)
            holder.tvMSSV = view.findViewById(R.id.tvMSSV)
            holder.imgDelete = view.findViewById(R.id.imgDelete)
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        // Gán dữ liệu vào UI
        val student = list[position]
        holder.tvName.text = student.name
        holder.tvMSSV.text = student.mssv

        // Xử lý nút XÓA
        holder.imgDelete.setOnClickListener {
            onDeleteClick(position)  // Gọi callback về MainActivity
        }

        return view
    }

    // Lưu view để tái sử dụng
    private class ViewHolder {
        lateinit var tvName: TextView
        lateinit var tvMSSV: TextView
        lateinit var imgDelete: ImageView
    }
}
