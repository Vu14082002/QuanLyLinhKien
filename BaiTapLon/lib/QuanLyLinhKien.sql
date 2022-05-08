create database QuanLyLinhKien

go
use QuanLyLinhKien
go
create table KhachHang( maKhachHang varchar(10)
						, ho nvarchar(30)
						, ten nvarchar(30)
						 ,sdt varchar(30)
						 , diaChi nvarchar(30)
						 , email varchar(100)
						 , gioiTinh bit
						 , primary key (maKhachHang)
						)
go
create table PhongBan( maPhongBan varchar(10)
						,tenPhongBan nvarchar(30)
						 ,primary key (maPhongBan)
						)
go
create table NhanVien( maNhanVien varchar(10)
						, ho nvarchar(30)
						, ten nvarchar(30)
						 ,sdt varchar(30)
						 , diaChi nvarchar(30)
						 , email varchar(100)
						 , gioiTinh bit
						 ,ngayVaoLam date
						 ,soCCCD varchar(15)
						 ,matKhau varchar(30)
						 ,primary key (maNhanVien)
						 ,maPhongBan varchar(10) foreign key REFERENCES  PhongBan(maPhongBan))

create table HoaDon ( maHoaDon varchar(10)
					  , maNhanVien varchar(10) foreign key REFERENCES  NhanVien(maNhanVien)
					  , maKhachHang varchar(10) foreign key REFERENCES  KhachHang(maKhachHang)
					  , ngayDatHang date
					  , ngayGiaoHang date
					  , ngayChuyen date
					  , noiNhanHang nvarchar(1000)
					  , primary  key (maHoaDon) )
create table LoaiLinhKien (
						 maLoai varchar(10),
						 tenLoai nvarchar(30),
						 primary key (maLoai)
						)
create table NhaCungCap (
						maNhaCungCap varchar(10),
						 tenNhaCungCap nvarchar(30)
						 , email varchar(100)
						 , diaChi nvarchar(100)
						 , soDienThoai varchar(15)
						 primary key (maNhaCungCap)
						 )
create table LinhKien(
						maLinhKien varchar(10)
						, tenLinhKien nvarchar(50)
						, soLuongTon int
						, diaChiHinhAnh varchar(30)
						, maLoai varchar(10) foreign key REFERENCES  LoaiLinhKien(maLoai)
						, maNCC varchar(10) foreign key REFERENCES  NhaCungCap(maNhaCungCap)
						, primary key (maLinhKien))
ALTER TABLE LinhKien
ADD donGia money;
create table chiTietHoaDon(
						 maHoaDon varchar(10) foreign key REFERENCES  HoaDon(maHoaDon)
						, maChiTietHoaDon varchar(10)
						, soLuong int
						, donGia money
						, thanhTien money
						, maLinhKien varchar(10) foreign key REFERENCES  LinhKien(maLinhKien)
						, primary key (maChiTietHoaDon,maHoaDon)
						)
						
backup database QuanLyLinhKien to disk ='D:\sqlBackup\QuanLyLinhKien.bak' 
select * from LoaiLinhKien
insert into LoaiLinhKien values ('mouse',N'Chuột máy tính')
insert into LoaiLinhKien values ('keyboard',N'Bàn phím máy tính');
insert into LoaiLinhKien values ('screen',N'Màn hình máy tính');
insert into LoaiLinhKien values ('accessory',N'Phụ kiện máy tính');
insert into LoaiLinhKien values ('camera',N'Camera an ninh');
select * from NhanVien
select * from PhongBan
insert into PhongBan values ('NS',N'Phòng nhân sự')
set DateFormat dmy

insert into NhanVien values
('AD123456','Nguyen','Hieu','0123456789','38 Nguyen Du','hieurio12@gmail.com',1,'14/08/1990',
		'012345678911','123456','NS')