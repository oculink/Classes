# Function: enter student information
def enter_student():
    name = input("Enter student name: ")
    age = int(input("Enter age: "))
    print(f"Student {name} (Age {age}) saved successfully.\n")

# Function: calculator
def calculator():
    try:
        num1 = float(input("Enter first number: "))
        num2 = float(input("Enter second number: "))
        
        print("\nChoose operation")
        print("1. Addition")
        print("2. Subtraction")
        print("3. Multiplication")
        print("4. Division")
        
        op = input("Enter operation: ")
        
        if op == "1":
            print("Result =", num1 + num2)
        elif op == "2":
            print("Result =", num1 - num2)
        elif op == "3":
            print("Result =", num1 * num2)
        elif op == "4":
            if num2 != 0:
                print("Result =", num1 / num2)
            else:
                print("Cannot divide by zero!")
        else:
            print("Invalid operation!")
            
    except ValueError:
        print("Invalid input! Please enter numbers only.")

# Function: main menu
def menu():
    while True:
        print("===== MAIN MENU =====")
        print("1. Enter Student Information")
        print("2. Calculator")
        print("3. Exit")
        
        choice = input("Select option (1-3): ")
        
        if choice == "1":
            enter_student()
        elif choice == "2":
            calculator()
        elif choice == "3":
            print("Exiting program... Goodbye!")
            break
        else:
            print("Invalid choice. Try again.\n")

# Run program
menu()