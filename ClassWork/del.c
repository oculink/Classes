#include <stdio.h>
#include <string.h>

#define TABLE_SIZE 11
#define PRIME 7
#define EMPTY -1
#define DELETED -2

typedef struct {
    int id;
    char name[50];
} Student;

Student table[TABLE_SIZE];

int hash1(int key) {
    return key % TABLE_SIZE;
}

int hash2(int key) {
    return PRIME - (key % PRIME);
}

void deleteRecord(int id) {
    printf("\n=== DELETE ID: %d ===\n", id);

    int h1_val = hash1(id);
    int h2_val = hash2(id);

    printf("h1(%d) = %d\n", id, h1_val);
    printf("h2(%d) = %d\n", id, h2_val);

    for (int i = 0; i < TABLE_SIZE; i++) {
        int index = (h1_val + i * h2_val) % TABLE_SIZE;
        printf("Probe %d: index = %d", i, index);

        if (table[index].id == id) {
            printf(" -> Found! Deleting...\n");
            table[index].id = DELETED;
            strcpy(table[index].name, "");
            printf("Deleted successfully!\n");
            return;
        } 
        else if (table[index].id == EMPTY) {
            printf(" -> Empty slot. Not found.\n");
            return;
        } 
        else {
            printf(" -> Continue...\n");
        }
    }

    printf("ID not found after full probing.\n");
}

int main() {
   
    for (int i = 0; i < TABLE_SIZE; i++) {
        table[i].id = EMPTY;
        strcpy(table[i].name, "");
    }

    table[2].id = 240601;
    strcpy(table[2].name, "y");

    deleteRecord(240601);
    deleteRecord(111111);

    return 0;
}