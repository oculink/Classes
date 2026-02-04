#include <stdio.h>
#include <string.h>

#define TABLE_SIZE 11
#define PRIME 7
#define EMPTY -1

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
        
        if (table[index].id == EMPTY) {
            printf(" -> Empty! Insert here.\n");
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
        } else {
            printf(" -> Wrong ID. Continue...\n");
        }
    }
    
    printf("Not found!\n");
    return -1;
}

void display() {
    printf("\n--- Hash Table ---\n");
    for (int i = 0; i < TABLE_SIZE; i++) {
        printf("[%d] ", i);
        if (table[i].id == EMPTY) {
            printf("EMPTY\n");
        } else {
            printf("ID: %d, Name: %s\n", table[i].id, table[i].name);
        }
    }
    printf("------------------\n");
}

int main() {
    initTable();
    
    printf("GMI DOUBLE HASHING DEMO\n");
    printf("Table Size: %d\n", TABLE_SIZE);
    printf("Formula: h(k,i) = [h1(k) + i*h2(k)] mod %d\n", TABLE_SIZE);
    
    insert(240797, "y");
    display();
    
    insert(240601, "a");
    display();
    
    insert(240523, "f");
    display();

    insert(240545, "s");
    display();
    
    search(240601);

    return 0;
}

