#include <stdio.h>
#include <string.h>

#define TABLE_SIZE 12
#define PRIME 7
#define EMPTY -1
#define DELETED -2

typedef struct {
    int id;
    char name[50];
} Student;

Student table[TABLE_SIZE];

void initTable() {
    for (int i = 0; i < TABLE_SIZE; i++) {
        table[i].id = EMPTY;
    }
}

int hash1(int key) {
    return key % TABLE_SIZE;
}

int hash2(int key) {
    return PRIME - (key % PRIME);
}

void insert(int id, char name[]) {
    printf("\n=== INSERT ID: %d ===\n", id);
    
    int h1_val = hash1(id);
    int h2_val = hash2(id);
    
    printf("h1(%d) = %d\n", id, h1_val);
    printf("h2(%d) = %d\n", id, h2_val);
    
    for (int i = 0; i < TABLE_SIZE; i++) {
        int index = (h1_val + i * h2_val) % TABLE_SIZE;
        
        printf("Probe %d: index = %d", i, index);
        
        if (table[index].id == EMPTY || table[index].id == DELETED) {
            printf(" -> Empty/Deleted! Insert here.\n");
            table[index].id = id;
            strcpy(table[index].name, name);
            printf("Success!\n");
            return;
        } else {
            printf(" -> Collision!\n");
        }
    }
    
    printf("Table full!\n");
}

// FIXED VERSION - Skip DELETED slots during search
int search(int id) {
    printf("\n=== SEARCH ID: %d ===\n", id);
    
    int h1_val = hash1(id);
    int h2_val = hash2(id);
    
    printf("h1(%d) = %d\n", id, h1_val);
    printf("h2(%d) = %d\n", id, h2_val);
    
    for (int i = 0; i < TABLE_SIZE; i++) {
        int index = (h1_val + i * h2_val) % TABLE_SIZE;
        
        printf("Probe %d: index = %d", i, index);
        
        if (table[index].id == id) {
            printf(" -> Found: %s\n", table[index].name);
            return index;
        } else if (table[index].id == EMPTY) {
            printf(" -> Empty slot. Not found.\n");
            return -1;
        } else if (table[index].id == DELETED) {
            printf(" -> Deleted slot. Continue searching...\n");
        } else {
            printf(" -> Wrong ID (%d). Continue...\n", table[index].id);
        }
    }
    
    printf("Searched all slots. Not found!\n");
    return -1;
}

void delete(int id) {
    printf("\n=== DELETE ID: %d ===\n", id);
    
    int index = search(id);
    if (index == -1) {
        printf("Cannot delete. ID not found.\n");
        return;
    }

    table[index].id = DELETED;
    strcpy(table[index].name, "");
    printf("ID %d deleted successfully!\n", id);
}

void display() {
    printf("\n--- Hash Table ---\n");
    for (int i = 0; i < TABLE_SIZE; i++) {
        printf("[%d] ", i);
        if (table[i].id == EMPTY) {
            printf("EMPTY\n");
        } else if (table[i].id == DELETED) {
            printf("DELETED\n");
        } else {
            printf("ID: %d, Name: %s\n", table[i].id, table[i].name);
        }
    }
    printf("------------------\n");
}

int main() {
    initTable();
    
    printf("FIXED DOUBLE HASHING WITH PROPER DELETE HANDLING\n");
    printf("Table Size: %d\n", TABLE_SIZE);
    printf("Formula: h(k,i) = [h1(k) + i*h2(k)] mod %d\n\n", TABLE_SIZE);
    
    insert(240797, "y");
    insert(240601, "a");
    insert(240523, "f");
    insert(240545, "s");
    display();
    
    search(240601);
    
    delete(240601);
    display();
    
    search(240523);
    
    insert(240888, "hh");
    display();
    
    search(999999);
    
    printf("\n=== TEST: Multiple of 9 (edge case) ===\n");
    insert(9, "TestNine");
    insert(18, "TestEighteen");
    display();
    
    search(9);
    search(18);
    
    return 0;
}