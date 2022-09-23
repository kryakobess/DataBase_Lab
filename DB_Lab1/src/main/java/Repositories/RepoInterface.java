package Repositories;

public interface RepoInterface<N> {
    public void Post(N element);
    public N GetById(long id);
    public void PatchById(long existedId, N editedElement);
    public void DeleteById(long id);
}
