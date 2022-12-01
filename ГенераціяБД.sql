CREATE DATABASE MobileСompany


USE MobileСompany

DROP TABLE Client
DROP TABLE Tariff

CREATE TABLE Tariff(
	TariffID int NOT NULL IDENTITY(1,1)  PRIMARY KEY,
	[Name] varchar(50) UNIQUE NOT NULL,
	SubPrice  float(2) NOT NULL,						--Ціна за місяць 
	MinutesByOperator int NOT NULL,						--Кількість хвилин 
	MinutesOtherOperators int NOT NULL,					--Кількість хвилин на інші оператори
	SMS int NOT NULL,
	MB int NOT NULL, 
	PriceExtraMinute float(2) NOT NULL,					--Ціна за додаткову хвилину
	PriceExtraSMS float(2) NOT NULL,					--Ціна за додаткову SMS
	PriceExtra_100MB float(2) NOT NULL,					--Ціна за додаткові 100 Мб
	SpecialCondition varchar(100) NULL					--Спеціальні умови
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



USE MobileСompany




INSERT INTO Tariff 
VALUES ('Твій супер',95,2000,500,10,3000,1.5,2,10,'None'),
	   ('Love',150,3000,1000,50,6000,1.5,2,10,'Невикористані мобільні дані зберігаються'),
	   ('Економ',50,1000,100,10,1000,1.5,2,10,'None'),
	   ('Бізнес',200,5000,3000,100,10000,1.5,2,10,'Можна підключити кілька SIM-карт')

	  

INSERT INTO Client 
VALUES ('123','380971953887','Бичковський','Ігор','Ярославович',1),
	   ('12332','380000000001','Бичковський','Владислав','Володимирович',2),
	   ('ASFE1sdg%#','380000000002','Іваночко','Іван','Іванович',null),
	   ('asfertv3341s','380000000003','Міліневський','Максим','Андрійовч',null),
	   ('81213asdf03(*№4','380000000004','Кузьмінський','Василь','Юрійович',1),
	   ('sdfr32','380000000005','Михайлишин','Олександр','Ігорович',3),
	   ('LKJFhHFSDKfhjhsdf','380000000006','Ткач','Тарас','Володимирович',2)



