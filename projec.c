#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ORDERS 100
#define MAX_ORDER_LENGTH 100

typedef struct {
    char orders[MAX_ORDERS][MAX_ORDER_LENGTH];
    int front;
    int rear;
    int count;
} Queue;

void initQueue(Queue *q);
int isEmpty(Queue *q);
int isFull(Queue *q);
void addOrder(Queue *q);
void removeOrder(Queue *q);
void listOrders(Queue *q);
void totalOrders(Queue *q);
void displayMenu();
void clearScreen();
void pauseScreen();

int main() {
    Queue orderQueue;
    char choice;
    
    initQueue(&orderQueue);
    
    do {
        displayMenu();
        printf("Enter your option: ");
        scanf(" %c", &choice);
        getchar();
        
        switch(choice) {
            case 'A':
            case 'a':
                addOrder(&orderQueue);
                break;
            case 'B':
            case 'b':
                removeOrder(&orderQueue);
                break;
            case 'C':
            case 'c':
                listOrders(&orderQueue);
                break;
            case 'D':
            case 'd':
                totalOrders(&orderQueue);
                break;
            case 'Q':
            case 'q':
                printf("\nThank you for using The Flavorful Fork system!\n");
                printf("Logging out...\n");
                pauseScreen();
                clearScreen();
                break;
            default:
                printf("\n[ERROR] Invalid option! Please choose A, B, C, D, or Q.\n");
                pauseScreen();
                clearScreen();
        }
    } while(choice != 'Q' && choice != 'q');
    
    return 0;
}

void initQueue(Queue *q) {
    q->front = 0;
    q->rear = -1;
    q->count = 0;
}

int isEmpty(Queue *q) {
    return (q->count == 0);
}

int isFull(Queue *q) {
    return (q->count == MAX_ORDERS);
}

void addOrder(Queue *q) {
    clearScreen();
    printf("\n========== ADD NEW ORDER ==========\n\n");
    
    if(isFull(q)) {
        printf("[ERROR] Queue is full! Cannot add more orders.\n");
        printf("Please complete some orders first.\n\n");
        pauseScreen();
        clearScreen();
        return;
    }
    
    char orderName[MAX_ORDER_LENGTH];
    printf("Enter the food order (e.g., Spaghetti Carbonara): ");
    fgets(orderName, MAX_ORDER_LENGTH, stdin);
    
    orderName[strcspn(orderName, "\n")] = 0;
    
    if(strlen(orderName) == 0) {
        printf("\n[ERROR] Order name cannot be empty!\n\n");
        pauseScreen();
        clearScreen();
        return;
    }
    
    q->rear = (q->rear + 1) % MAX_ORDERS;
    strcpy(q->orders[q->rear], orderName);
    q->count++;
    
    printf("\n[SUCCESS] Order '%s' has been added to the queue!\n", orderName);
    printf("Order Number: %d\n\n", q->count);
    pauseScreen();
    clearScreen();
}

void removeOrder(Queue *q) {
    clearScreen();
    printf("\n========== REMOVE COMPLETED ORDER ==========\n\n");
    
    if(isEmpty(q)) {
        printf("[ERROR] No orders in the queue!\n");
        printf("The queue is empty.\n\n");
        pauseScreen();
        clearScreen();
        return;
    }
    
    printf("Removing order: '%s'\n", q->orders[q->front]);
    
    q->front = (q->front + 1) % MAX_ORDERS;
    q->count--;
    
    printf("\n[SUCCESS] Order has been completed and removed from queue!\n");
    printf("Remaining orders: %d\n\n", q->count);
    pauseScreen();
    clearScreen();
}

void listOrders(Queue *q) {
    clearScreen();
    printf("\n========== LIST OF CURRENT ORDERS ==========\n\n");
    
    if(isEmpty(q)) {
        printf("No orders in the queue.\n");
        printf("The kitchen is ready for new orders!\n\n");
        pauseScreen();
        clearScreen();
        return;
    }
    
    printf("%-10s %-50s\n", "Order #", "Food Item");
    printf("---------------------------------------------------------------\n");
    
    int index = q->front;
    for(int i = 0; i < q->count; i++) {
        printf("%-10d %-50s\n", i + 1, q->orders[index]);
        index = (index + 1) % MAX_ORDERS;
    }
    
    printf("---------------------------------------------------------------\n");
    printf("Total orders in queue: %d\n\n", q->count);
    pauseScreen();
    clearScreen();
}

void totalOrders(Queue *q) {
    clearScreen();
    printf("\n========== TOTAL NUMBER OF ORDERS ==========\n\n");
    printf("Current orders in queue: %d\n", q->count);
    printf("Maximum capacity: %d\n", MAX_ORDERS);
    printf("Available slots: %d\n\n", MAX_ORDERS - q->count);
    
    if(q->count == 0) {
        printf("Status: Queue is EMPTY\n");
    } else if(q->count == MAX_ORDERS) {
        printf("Status: Queue is FULL\n");
    } else {
        printf("Status: Queue is ACTIVE\n");
    }
    
    printf("\n");
    pauseScreen();
    clearScreen();
}

void displayMenu() {
    printf("\n");
    printf("*************************************************\n");
    printf("*************  WELCOME TO  **********************\n");
    printf("*************  THE FLAVORFUL FORK  **************\n");
    printf("*************************************************\n");
    printf("*                                               *\n");
    printf("*  A: Add Order                                 *\n");
    printf("*  B: Remove Order                              *\n");
    printf("*  C: List of Orders                            *\n");
    printf("*  D: Total Number of Orders                    *\n");
    printf("*  Q: Quit or Logout                            *\n");
    printf("*                                               *\n");
    printf("*************************************************\n");
    printf("\n");
}

void clearScreen() {
    #ifdef _WIN32
        system("cls");
    #else
        system("clear");
    #endif
}

void pauseScreen() {
    printf("Press Enter to continue...");
    getchar();
}