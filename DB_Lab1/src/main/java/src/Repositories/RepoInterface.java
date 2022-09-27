package src.Repositories;

public interface RepoInterface<N> {
    public int Post(N element);
    public N GetById(long id);
    public void PatchById(long existedId, N editedElement);
    public void DeleteById(long id);
}
