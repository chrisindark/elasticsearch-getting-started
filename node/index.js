const {Client} = require('@elastic/elasticsearch');

// Create Elasticsearch client instance
const client = new Client({
    node: 'http://localhost:9201'
});

const CATS_CREATE_LIMIT = 1000000;
const BATCH_SIZE = 10000

getRandomInt = (min, max) => {
    return Math.floor(Math.random() * (max - min + 1)) + min;
};

generateRandomCat = () => {
    const names = [
      'Whiskers',
      'Smokey',
      'Mittens',
      'Luna',
      'Tiger',
      'Simba',
      'Oreo',
      'Bella',
      'Max',
      'Charlie',
    ];
    const breeds = [
      'Siamese',
      'Persian',
      'Maine Coon',
      'Ragdoll',
      'Bengal',
      'Sphynx',
      'British Shorthair',
      'Scottish Fold',
      'Russian Blue',
    ];
    const name = names[getRandomInt(0, names.length - 1)];
    const age = this.getRandomInt(1, 20);
    const breed = breeds[getRandomInt(0, breeds.length - 1)];
    return { name, age, breed };
  };

  create = async () => {
    try {
      let counter = 0;
      while (true) {
        if (counter < CATS_CREATE_LIMIT) {
          const catsToInsert = [];
          for (let i = 0; i < BATCH_SIZE; ++i) {
            catsToInsert.push(this.generateRandomCat());
          }
          const response = await this.catsService.createMany(catsToInsert);
          this.logger.log(counter);

          counter += BATCH_SIZE;
        } else {
          break;
        }
      }
    } catch (e) {}
  };

async function indexDocument() {
    try {
        const indexParams = {
            index: 'test', // Specify the index name
            body: {
                id: 1,
                title: 'Sample Document',
                content: 'This is a sample document indexed in Elasticsearch.',
                createdAt: new Date(),
                updatedAt: new Date(),
            },
        };

        const response = await client.index(indexParams);
        console.log('Document indexed successfully:', response.body);
    } catch (e) {
        console.error('Error indexing document: ', e);
    }
}

// Call the function to index the document
indexDocument();
