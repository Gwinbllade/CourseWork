CREATE DATABASE Mobile�ompany


USE Mobile�ompany

DROP TABLE Client
DROP TABLE Tariff

CREATE TABLE Tariff(
	TariffID int NOT NULL IDENTITY(1,1)  PRIMARY KEY,
	[Name] varchar(50) UNIQUE NOT NULL,
	SubPrice  float(2) NOT NULL,						--ֳ�� �� ����� 
	MinutesByOperator int NOT NULL,						--ʳ������ ������ 
	MinutesOtherOperators int NOT NULL,					--ʳ������ ������ �� ���� ���������
	SMS int NOT NULL,
	MB int NOT NULL, 
	PriceExtraMinute float(2) NOT NULL,					--ֳ�� �� ��������� �������
	PriceExtraSMS float(2) NOT NULL,					--ֳ�� �� ��������� SMS
	PriceExtra_100MB float(2) NOT NULL,					--ֳ�� �� �������� 100 ��
	SpecialCondition varchar(100) NULL					--��������� �����
)

CREATE TABLE Client(
	ClientID int NOT NULL IDENTITY(1,1)  PRIMARY KEY,
	[Password] varchar(100) NOT NULL, 
	PhoneNumber varchar(12) NOT NULL UNIQUE,
	Surname varchar(50) NOT NULL,
	[Name] varchar (50) NOT NULL,
	Patronymic varchar (50) NOT NULL,
	TariffID int FOREIGN KEY REFERENCES dbo.Tariff(TariffID) ON DELETE SET NULL NULL
)



USE Mobile�ompany




INSERT INTO Tariff 
VALUES ('��� �����',95,2000,500,10,3000,1.5,2,10,'None'),
	   ('Love',150,3000,1000,50,6000,1.5,2,10,'������������ ������ ��� �����������'),
	   ('������',50,1000,100,10,1000,1.5,2,10,'None'),
	   ('������',200,5000,3000,100,10000,1.5,2,10,'����� ��������� ����� SIM-����')

	  

INSERT INTO Client 
VALUES ('123','380971953887','�����������','����','�����������',1),
	   ('12332','380000000001','�����������','���������','�������������',2),
	   ('ASFE1sdg%#','380000000002','��������','����','��������',null),
	   ('asfertv3341s','380000000003','̳���������','������','��������',null),
	   ('81213asdf03(*�4','380000000004','�����������','������','�������',1),
	   ('sdfr32','380000000005','����������','���������','��������',3),
	   ('LKJFhHFSDKfhjhsdf','380000000006','����','�����','�������������',2)



