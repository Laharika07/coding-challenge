create database hospital;
use hospital;

create table Patient(
patientId int primary key,
firstName varchar(255),
lastName  varchar(255),
dateOfBirth date,
gender varchar(10),
contactNumber varchar(20),
address varchar(255));




create table Doctor (
    doctorId INT PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    specialization VARCHAR(255),
    contactNumber VARCHAR(20)
);



create table Appointment (
    appointmentId INT PRIMARY KEY,
    patientId INT,
    doctorId INT,
    appointmentDate DATE,
    description VARCHAR(255),
    foreign key (patientId) references Patient(patientId),
    foreign key  (doctorId) references Doctor(doctorId)
);


insert into Patient (patientId, firstName, lastName, dateOfBirth, gender, contactNumber, address)
values
(1, 'Eren', 'Jeager', '2000-05-15', 'Male', '123-456-7890', '123 Eldia St'),
(2, 'Mikasa', 'Ackerman', '2003-08-20', 'Female', '987-654-3210', '456 Eldia St'),
(3, 'Armin', 'Arlet', '2001-11-25', 'Male', '555-555-5555', '789 Marley St'),
(4, 'Levi', 'Ackerman', '1997-03-10', 'Male', '333-333-3333', '321 Marley St'),
(5, 'Annie', 'Leonhart', '1995-09-05', 'Female', '777-777-7777', '555 Eldia St');

select * from Patient;

insert into Doctor (doctorId, firstName, lastName, specialization, contactNumber)
values
(101, 'Dr. Sinha', 'Sagar', 'Cardiology', '111-222-3333'),
(102, 'Dr. Sara', 'Sunil', 'Pediatrics', '444-555-6666'),
(103, 'Dr. Robert', 'John', 'Dermatology', '777-888-9999'),
(104, 'Dr. Millie', 'Brown', 'Neurology', '123-456-7890'),
(105, 'Dr. Michael', 'Miller', 'Opthamologist', '555-444-3333');

select * from Doctor;

insert into Appointment (appointmentId, patientId, doctorId, appointmentDate, description)
values
(201, 1, 101, '2024-04-01', 'Routine checkup'),
(202, 2, 102, '2024-04-05', 'Follow-up consultation'),
(203, 3, 103, '2024-04-10', 'Skin allergy treatment'),
(204, 4, 104, '2024-04-15', 'Neurological problem'),
(205, 5, 105, '2024-04-20', 'Eye sight checkup');

select * from Appointment;








