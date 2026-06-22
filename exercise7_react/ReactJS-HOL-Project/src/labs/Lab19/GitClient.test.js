import axios from 'axios';
import GitClient from './GitClient';

jest.mock('axios');

describe('Git Client Tests', () => {

  test('should return repository names for techiesyed', () => {
    // Mock axios to return dummy data instead of calling api.github.com
    const mockRepos = {
      data: [
        { name: 'appscentrsolitions' },
        { name: 'ArrayListDemo' },
        { name: 'ArrayListDemo01' },
        { name: 'AzureDevopsDemoProductsApi' },
        { name: 'CleanArchitecture' },
      ]
    };

    axios.get.mockResolvedValue(mockRepos);

    return GitClient.getRepositories('techiesyed').then(response => {
      expect(response.data).toEqual(mockRepos.data);
      expect(response.data.length).toBe(5);
      expect(response.data[0].name).toBe('appscentrsolitions');
      expect(axios.get).toHaveBeenCalledWith(
        'https://api.github.com/users/techiesyed/repos'
      );
    });
  });

});
