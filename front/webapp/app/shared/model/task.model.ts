export interface ITask {
  id?: number;
  title?: string | null;
  description?: string | null;
  status?: boolean | null;
}

export const defaultValue: Readonly<ITask> = {
  status: false,
};
