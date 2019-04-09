#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/mman.h>
#define SEM_MUTEX_NAME "/sem-mutex"
#define SHARED_MEM_NAME "/posix-shared-mem-example"
#define SEM_SPOOL_SIGNAL_NAME "/sem-spool-signal"

struct shared_memory
{
	int Number;
	int Number_of_readers;
	int Number_of_Clients;
	int Active_Client;
};

int main ()
{
	struct shared_memory *shared_memory_ptr;
	sem_t *mutex_sem, *spool_signal_sem ;

	int fd_shm;
	mutex_sem = sem_open (SEM_MUTEX_NAME ,O_CREAT, 0644, 0);
	
	fd_shm = shm_open (SHARED_MEM_NAME, O_CREAT|O_RDWR, S_IRWXU);

	ftruncate (fd_shm, sizeof (struct shared_memory));

	shared_memory_ptr = mmap (NULL, sizeof (struct shared_memory),PROT_READ | PROT_WRITE, MAP_SHARED,fd_shm, 0);
	
	shared_memory_ptr ->Number=0;
	shared_memory_ptr ->Number_of_Clients=0;
	shared_memory_ptr ->Number_of_readers++;

	spool_signal_sem = sem_open (SEM_SPOOL_SIGNAL_NAME, O_CREAT, 0644, 0)

	//Initialisation over
	printf("Initialisation Over!\n");
	sem_post (mutex_sem); 
	error ("sem_post: mutex_sem");
	
	while(1){
	// forever
		if (sem_wait (spool_signal_sem) == -1)
			error ("sem_wait: spool_signal_sem");

		printf("Value of Number is: %d \t Incremented By %d\n",shared_memory_ptr->Number,shared_memory_ptr->Active_Client);

		if(shared_memory_ptr->Number ==(10*(shared_memory_ptr->Number_of_Clients))){
			printf("\nEnough With the incremnting\nServer Shuting Down!\n");
			exit(1);
		}
	}
}