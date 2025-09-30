
// Hàm main - Chương trình chính
fun main() {
    // 1. Nhập mảng phân số
    print("Nhập số lượng phân số: ")
    val n = readln().toInt()

    val mangPhanSo = Array(n) { PhanSo() }

    println("\n=== NHẬP MẢNG PHÂN SỐ ===")
    for (i in mangPhanSo.indices) {
        println("\nPhân số thứ ${i + 1}:")
        mangPhanSo[i].nhap()
    }

    // 2. In mảng phân số vừa nhập
    println("\n=== MẢNG PHÂN SỐ VỪA NHẬP ===")
    for (i in mangPhanSo.indices) {
        print("Phân số ${i + 1}: ")
        mangPhanSo[i].inPhanSo()
        println()
    }

    // 3. Tối giản các phần tử và in kết quả
    println("\n=== TỐI GIẢN CÁC PHÂN SỐ ===")
    for (i in mangPhanSo.indices) {
        mangPhanSo[i].toiGian()
        print("Phân số ${i + 1} sau tối giản: ")
        mangPhanSo[i].inPhanSo()
        println()
    }

    // 4. Tính tổng các phân số
    println("\n=== TỔNG CÁC PHÂN SỐ ===")
    var tong = mangPhanSo[0].copy()
    for (i in 1 until mangPhanSo.size) {
        tong = tong.cong(mangPhanSo[i])
    }
    print("Tổng = ")
    tong.inPhanSo()
    println()

    // 5. Tìm phân số lớn nhất
    println("\n=== PHÂN SỐ LỚN NHẤT ===")
    var max = mangPhanSo[0]
    var viTriMax = 0

    for (i in 1 until mangPhanSo.size) {
        if (mangPhanSo[i].soSanh(max) > 0) {
            max = mangPhanSo[i]
            viTriMax = i
        }
    }

    print("Phân số lớn nhất là: ")
    max.inPhanSo()
    println(" (vị trí ${viTriMax + 1})")

    // 6. Sắp xếp mảng giảm dần
    println("\n=== SẮP XẾP GIẢM DẦN ===")

    // Sử dụng thuật toán sắp xếp nổi bọt (Bubble Sort)
    for (i in mangPhanSo.indices) {
        for (j in 0 until mangPhanSo.size - i - 1) {
            if (mangPhanSo[j].soSanh(mangPhanSo[j + 1]) < 0) {
                // Hoán đổi
                val temp = mangPhanSo[j]
                mangPhanSo[j] = mangPhanSo[j + 1]
                mangPhanSo[j + 1] = temp
            }
        }
    }

    println("Mảng sau khi sắp xếp giảm dần:")
    for (i in mangPhanSo.indices) {
        print("Phân số ${i + 1}: ")
        mangPhanSo[i].inPhanSo()
        println()
    }
}