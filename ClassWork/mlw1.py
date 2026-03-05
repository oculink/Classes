# Note from me: LET ME OUT OF THIS CLASS !!!! LET ME LEARN MORE COMPLEX STUFF !!! THIS IS TOO EASY FOR ME !!!! I WANT TO LEARN PYTHON, NOT THIS BASIC STUFF !!!! I WANT TO LEARN HOW TO MAKE GAMES, HOW TO MAKE APPS, HOW TO MAKE WEBSITES, HOW TO MAKE AI, HOW TO MAKE ROBOTS, HOW TO MAKE EVERYTHING !!!! PLEASE LET ME OUT OF THIS CLASS AND LET ME LEARN MORE ADVANCED TOPICS !!!! I PROMISE I WILL WORK HARD AND LEARN FAST !!!! PLEASE !!!!!!!!!

while True:
    print("===== MAIN MENU =====")
    print("1. Enter your name")
    print("2. Calculate addition")
    print("3. Exit")
    
    choice = input("Enter your choice (1-3): ")
    
    # Option 1
    if choice == "1":
        name = input("Enter your name: ")
        print("Hello,", name)
        
    # Option 2
    elif choice == "2":
        num1 = int(input("Enter first number: "))
        num2 = int(input("Enter second number: "))
        total = num1 + num2
        print("Total =", total)
        
    # Option 3 (Exit)
    elif choice == "3":
        print("Program ended. Goodbye!")
        break
        
    # Invalid choice
    else:
        print("Invalid choice. Please try again.")

