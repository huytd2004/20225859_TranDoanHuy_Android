// Lớp Phân Số
class PhanSo(private var tuSo: Int = 0, private var mauSo: Int = 1) {

    // Phương thức nhập phân số từ bàn phím
    fun nhap() {
        println("Nhập phân số:")

        // Nhập tử số (không cho phép 0)
        do {
            print("Nhập tử số (khác 0): ")
            tuSo = readln().toInt()
            if (tuSo == 0) {
                println("Tử số phải khác 0. Vui lòng nhập lại!")
            }
        } while (tuSo == 0)

        // Nhập mẫu số (không cho phép 0)
        do {
            print("Nhập mẫu số (khác 0): ")
            mauSo = readln().toInt()
            if (mauSo == 0) {
                println("Mẫu số phải khác 0. Vui lòng nhập lại!")
            }
        } while (mauSo == 0)
    }

    // Phương thức in phân số
    fun inPhanSo() {
        print("$tuSo/$mauSo")
    }

    // Tìm ước chung lớn nhất (UCLN)
    private fun timUCLN(a: Int, b: Int): Int {
        var x = Math.abs(a)
        var y = Math.abs(b)
        while (y != 0) {
            val temp = y
            y = x % y
            x = temp
        }
        return x
    }

    // Phương thức tối giản phân số
    fun toiGian() {
        val ucln = timUCLN(tuSo, mauSo)
        tuSo /= ucln
        mauSo /= ucln

        // Đưa dấu âm về tử số
        if (mauSo < 0) {
            tuSo = -tuSo
            mauSo = -mauSo
        }
    }

    // Phương thức so sánh với phân số khác
    fun soSanh(ps: PhanSo): Int {
        val giaTriThis = this.tuSo.toDouble() / this.mauSo
        val giaTriPs = ps.tuSo.toDouble() / ps.mauSo

        return when {
            giaTriThis < giaTriPs -> -1
            giaTriThis > giaTriPs -> 1
            else -> 0
        }
    }

    // Phương thức tính tổng với một phân số
    fun cong(ps: PhanSo): PhanSo {
        val tuMoi = this.tuSo * ps.mauSo + ps.tuSo * this.mauSo
        val mauMoi = this.mauSo * ps.mauSo
        val ketQua = PhanSo(tuMoi, mauMoi)
        ketQua.toiGian()
        return ketQua
    }

    // Tạo bản sao của phân số
    fun copy(): PhanSo {
        return PhanSo(this.tuSo, this.mauSo)
    }
}