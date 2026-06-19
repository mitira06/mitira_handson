-- EXERCISE 1

DECLARE
BEGIN
    FOR cust IN (
        SELECT CustomerID,
               FLOOR(MONTHS_BETWEEN(SYSDATE,DOB)/12) Age
        FROM Customers
    )
    LOOP
        IF cust.Age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = cust.CustomerID;
        END IF;
    END LOOP;
END;
/

BEGIN
    FOR cust IN (SELECT CustomerID,Balance FROM Customers)
    LOOP
        IF cust.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP='Y'
            WHERE CustomerID=cust.CustomerID;
        END IF;
    END LOOP;
END;
/

BEGIN
    FOR loan_rec IN (
        SELECT CustomerID, EndDate
        FROM Loans
        WHERE EndDate BETWEEN SYSDATE AND SYSDATE+30
    )
    LOOP
        DBMS_OUTPUT.PUT_LINE(
        'Reminder for Customer '||loan_rec.CustomerID);
    END LOOP;
END;
/

-- EXERCISE 2

CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from NUMBER,
    p_to NUMBER,
    p_amount NUMBER
)
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID=p_from;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001,'Insufficient Funds');
    END IF;

    UPDATE Accounts
    SET Balance=Balance-p_amount
    WHERE AccountID=p_from;

    UPDATE Accounts
    SET Balance=Balance+p_amount
    WHERE AccountID=p_to;

    COMMIT;
END;
/

CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_empid NUMBER,
    p_percent NUMBER
)
IS
BEGIN
    UPDATE Employees
    SET Salary=Salary+Salary*p_percent/100
    WHERE EmployeeID=p_empid;
END;
/

CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_id NUMBER,
    p_name VARCHAR2,
    p_dob DATE,
    p_balance NUMBER
)
IS
BEGIN
    INSERT INTO Customers
    VALUES(
        p_id,
        p_name,
        p_dob,
        p_balance,
        SYSDATE,
        'N'
    );
END;
/

-- EXERCISE 3

CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest
IS
BEGIN
    UPDATE Accounts
    SET Balance=Balance+Balance*0.01
    WHERE AccountType='Savings';
END;
/

CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_dept VARCHAR2,
    p_bonus NUMBER
)
IS
BEGIN
    UPDATE Employees
    SET Salary=Salary+Salary*p_bonus/100
    WHERE Department=p_dept;
END;
/

CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from NUMBER,
    p_to NUMBER,
    p_amount NUMBER
)
IS
BEGIN
    UPDATE Accounts
    SET Balance=Balance-p_amount
    WHERE AccountID=p_from;

    UPDATE Accounts
    SET Balance=Balance+p_amount
    WHERE AccountID=p_to;
END;
/

-- EXERCISE 4

CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob DATE
)
RETURN NUMBER
IS
BEGIN
    RETURN FLOOR(MONTHS_BETWEEN(SYSDATE,p_dob)/12);
END;
/

CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan NUMBER,
    p_rate NUMBER,
    p_years NUMBER
)
RETURN NUMBER
IS
BEGIN
    RETURN (p_loan + (p_loan*p_rate/100*p_years))
           /(p_years*12);
END;
/

CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account NUMBER,
    p_amount NUMBER
)
RETURN VARCHAR2
IS
    v_balance NUMBER;
BEGIN
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID=p_account;

    IF v_balance >= p_amount THEN
        RETURN 'TRUE';
    ELSE
        RETURN 'FALSE';
    END IF;
END;
/

-- EXERCISE 5

CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog(
        TransactionID,
        LogDate,
        Description
    )
    VALUES(
        :NEW.TransactionID,
        SYSDATE,
        'Transaction Added'
    );
END;
/

CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    SELECT Balance
    INTO v_balance
    FROM Accounts
    WHERE AccountID=:NEW.AccountID;

    IF :NEW.TransactionType='Withdrawal'
       AND :NEW.Amount > v_balance THEN
        RAISE_APPLICATION_ERROR(
        -20002,
        'Withdrawal exceeds balance');
    END IF;
END;
/
SELECT object_name,
       object_type,
       status
FROM user_objects
WHERE object_type IN
('PACKAGE','PACKAGE BODY')
ORDER BY object_name;

-- PACKAGE

CREATE OR REPLACE PACKAGE CustomerManagement AS

    PROCEDURE AddCustomer(
        p_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    );

    FUNCTION GetBalance(
        p_customerid NUMBER
    ) RETURN NUMBER;

END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    )
    IS
    BEGIN
        INSERT INTO Customers
        VALUES(
            p_id,
            p_name,
            p_dob,
            p_balance,
            SYSDATE,
            'N'
        );
    END;

    FUNCTION GetBalance(
        p_customerid NUMBER
    )
    RETURN NUMBER
    IS
        v_balance NUMBER;
    BEGIN
        SELECT Balance
        INTO v_balance
        FROM Customers
        WHERE CustomerID=p_customerid;

        RETURN v_balance;
    END;

END CustomerManagement;
/

-- =====================================
-- EXERCISE 6 : CURSORS
-- =====================================

-- Scenario 1 : Generate Monthly Statements

DECLARE
    CURSOR GenerateMonthlyStatements IS
        SELECT TransactionID,
               AccountID,
               Amount,
               TransactionType
        FROM Transactions
        WHERE EXTRACT(MONTH FROM TransactionDate) =
              EXTRACT(MONTH FROM SYSDATE);

    v_tid NUMBER;
    v_acc NUMBER;
    v_amt NUMBER;
    v_type VARCHAR2(20);

BEGIN

    OPEN GenerateMonthlyStatements;

    LOOP

        FETCH GenerateMonthlyStatements
        INTO v_tid,v_acc,v_amt,v_type;

        EXIT WHEN GenerateMonthlyStatements%NOTFOUND;

        DBMS_OUTPUT.PUT_LINE(
            'Transaction ID: '||v_tid||
            ' Account: '||v_acc||
            ' Amount: '||v_amt||
            ' Type: '||v_type
        );

    END LOOP;

    CLOSE GenerateMonthlyStatements;

END;
/

-- Scenario 2 : Apply Annual Fee

DECLARE

    CURSOR ApplyAnnualFee IS
        SELECT AccountID
        FROM Accounts;

BEGIN

    FOR acc IN ApplyAnnualFee LOOP

        UPDATE Accounts
        SET Balance = Balance - 100
        WHERE AccountID = acc.AccountID;

    END LOOP;

    COMMIT;

END;
/

-- Scenario 3 : Update Loan Interest Rates

DECLARE

    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID,
               InterestRate
        FROM Loans;

BEGIN

    FOR loan_rec IN UpdateLoanInterestRates LOOP

        UPDATE Loans
        SET InterestRate = loan_rec.InterestRate + 0.5
        WHERE LoanID = loan_rec.LoanID;

    END LOOP;

    COMMIT;

END;
/

-- =====================================
-- EXERCISE 7 : EMPLOYEEMANAGEMENT PACKAGE
-- =====================================

CREATE OR REPLACE PACKAGE EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hiredate DATE
    );

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_salary NUMBER
    );

    FUNCTION AnnualSalary(
        p_id NUMBER
    ) RETURN NUMBER;

END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hiredate DATE
    )
    IS
    BEGIN

        INSERT INTO Employees
        VALUES(
            p_id,
            p_name,
            p_position,
            p_salary,
            p_department,
            p_hiredate
        );

    END;

    PROCEDURE UpdateEmployee(
        p_id NUMBER,
        p_salary NUMBER
    )
    IS
    BEGIN

        UPDATE Employees
        SET Salary = p_salary
        WHERE EmployeeID = p_id;

    END;

    FUNCTION AnnualSalary(
        p_id NUMBER
    )
    RETURN NUMBER
    IS
        v_salary NUMBER;
    BEGIN

        SELECT Salary
        INTO v_salary
        FROM Employees
        WHERE EmployeeID = p_id;

        RETURN v_salary * 12;

    END;

END EmployeeManagement;
/

-- =====================================
-- EXERCISE 7 : ACCOUNTOPERATIONS PACKAGE
-- =====================================

CREATE OR REPLACE PACKAGE AccountOperations AS

    PROCEDURE OpenAccount(
        p_accid NUMBER,
        p_custid NUMBER,
        p_type VARCHAR2,
        p_balance NUMBER
    );

    PROCEDURE CloseAccount(
        p_accid NUMBER
    );

    FUNCTION TotalBalance(
        p_custid NUMBER
    ) RETURN NUMBER;

END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_accid NUMBER,
        p_custid NUMBER,
        p_type VARCHAR2,
        p_balance NUMBER
    )
    IS
    BEGIN

        INSERT INTO Accounts
        VALUES(
            p_accid,
            p_custid,
            p_type,
            p_balance,
            SYSDATE
        );

    END;

    PROCEDURE CloseAccount(
        p_accid NUMBER
    )
    IS
    BEGIN

        DELETE FROM Accounts
        WHERE AccountID = p_accid;

    END;

    FUNCTION TotalBalance(
        p_custid NUMBER
    )
    RETURN NUMBER
    IS
        v_total NUMBER;
    BEGIN

        SELECT SUM(Balance)
        INTO v_total
        FROM Accounts
        WHERE CustomerID = p_custid;

        RETURN NVL(v_total,0);

    END;

END AccountOperations;
/

-- =====================================
-- FINAL VALIDATION
-- =====================================

SELECT object_name,
       object_type,
       status
FROM user_objects
WHERE object_type IN
('PROCEDURE',
 'FUNCTION',
 'TRIGGER',
 'PACKAGE',
 'PACKAGE BODY')
ORDER BY object_type, object_name;